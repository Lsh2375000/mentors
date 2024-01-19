package kr.nomadlab.mentors.payment.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.exception.CustomLogicException;
import kr.nomadlab.mentors.exception.ExceptionCode;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.payment.config.TossPaymentConfig;
import kr.nomadlab.mentors.payment.dto.PaymentDTO;
import kr.nomadlab.mentors.payment.dto.PaymentFailDto;
import kr.nomadlab.mentors.payment.dto.PaymentPAO;
import kr.nomadlab.mentors.payment.dto.PaymentSuccessDto;
import kr.nomadlab.mentors.payment.mapper.PaymentMapper;
import kr.nomadlab.mentors.payment.vo.PaymentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    private final ModelMapper modelMapper;
    private final PaymentMapper paymentMapper;
    private final TossPaymentConfig tossPaymentConfig;
    private final MemberMapper memberMapper;

    @Override
    public PaymentDTO paymentReqRegister(MemberSecurityDTO memberSecurityDTO, PaymentPAO paymentPAO) {
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .orderId(UUID.randomUUID().toString())
                .payType(paymentPAO.getPayType())
                .amount(paymentPAO.getAmount())
                .orderId(UUID.randomUUID().toString())
                .orderName(paymentPAO.getOrderName())
                .mno(memberSecurityDTO.getMno())
                .memberName(memberSecurityDTO.getMemberName())
                .memberId(memberSecurityDTO.getMemberId())
                .build();
        PaymentVO paymentVO = modelMapper.map(paymentDTO, PaymentVO.class);
        log.info(paymentVO);
        paymentMapper.paymentReqInsert(paymentVO);

        return paymentDTO;
    }

    @Override
    public PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount) {
        PaymentVO paymentVO = verifyPayment(orderId, amount);

        //최종 결제 승인 요청
        PaymentSuccessDto paymentSuccessDto = requestPaymentAccept(paymentKey, orderId, amount);

        PaymentDTO paymentDTO = modelMapper.map(paymentVO, PaymentDTO.class);
        paymentDTO.setPaymentKey(paymentKey);
        paymentDTO.setPaySuccessYN(true);
        Long mno = paymentVO.getMno();
        int coin = (int)((amount)/1000);
        memberMapper.updateCoin(mno,coin);

        //성공시 DB업데이트
        PaymentVO paymentVOUp = modelMapper.map(paymentDTO, PaymentVO.class);
        paymentMapper.update(paymentVOUp);

        // ------------멤버에 코인 추가하는 메서드 넣기 ------->
        //memberService.update();

        return paymentSuccessDto;
    }

    @Override
    public PaymentFailDto tossPaymentFail(String code, String message, String orderId) {
        log.info("----------------"+message);
        PaymentVO paymentVO = paymentMapper.findByOrderId(orderId);

        PaymentFailDto paymentFailDto = null;
        if (paymentVO == null) {
            paymentFailDto = PaymentFailDto.builder()
                    .errorCode("PAYMENT_NOT_FOUND")
                    .errorMessage("결제내역을 찾을 수 없습니다.")
                    .orderId(orderId).build();
        }
        else {
            if(!paymentVO.isPaySuccessYN()) {
                PaymentDTO paymentDTO = modelMapper.map(paymentVO, PaymentDTO.class);
                paymentDTO.setFailReason(message);
                paymentDTO.setPaySuccessYN(false);
                paymentDTO.setPayFailYN(true);
                PaymentVO resultPaymentVO = modelMapper.map(paymentDTO, PaymentVO.class);

                paymentMapper.update(resultPaymentVO);
            }

            paymentFailDto = PaymentFailDto.builder()
                    .errorCode(code)
                    .errorMessage(message)
                    .orderId(orderId).build();
        }

        return paymentFailDto;
    }
    //페이징된 구매내역 불러오기
    @Override
    public PageResponseDTO<PaymentDTO> getListPayments(Long mno, PageRequestDTO pageRequestDTO) {
        List<PaymentVO> paymentVOList = paymentMapper.selectList(mno, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        paymentVOList.forEach(paymentVO -> {
            PaymentDTO paymentDTO = modelMapper.map(paymentVO, PaymentDTO.class);
            paymentDTOList.add(paymentDTO);
        });

        return PageResponseDTO.<PaymentDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(paymentDTOList)
                .total(paymentMapper.getCount(mno))
                .build();
    }

    //주문번호 존재여부, 금액 확인
    private PaymentVO verifyPayment(String orderId, Long amount){
        PaymentVO paymentVO = paymentMapper.findByOrderId(orderId);
        if(paymentVO == null){
            throw new CustomLogicException(ExceptionCode.PAYMENT_NOT_FOUND);
        }
        if(!paymentVO.getAmount().equals(amount)){
            throw new CustomLogicException(ExceptionCode.PAYMENT_AMOUNT_EXP);
        }
        return paymentVO;
    }

    //최종결제를 위해 toss로 보낼 정보 담기
    private PaymentSuccessDto requestPaymentAccept(String paymentKey, String orderId, Long amount){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        JSONObject params = new JSONObject();

        params.put("orderId", orderId);
        params.put("amount", amount);

        PaymentSuccessDto result = null;
        try{
            result = restTemplate.postForObject(TossPaymentConfig.URL +paymentKey,
                    //최종 결제 승인
                    new HttpEntity<>(params, headers),
                    PaymentSuccessDto.class);
            //restTemplate.postForObject() --> POST 요청을 보내고 객체로 결과를 반환받는다.
        } catch (Exception e){
            throw new CustomLogicException(ExceptionCode.ALREADY_APPROVED);
        }
        return result;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        String encodeAuthKey = new String(
                Base64.getEncoder().encode((tossPaymentConfig.getTestSecretKey() + ":").getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodeAuthKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }
}
