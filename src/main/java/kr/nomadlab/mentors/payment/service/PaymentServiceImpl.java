package kr.nomadlab.mentors.payment.service;

import kr.nomadlab.mentors.exception.CustomLogicException;
import kr.nomadlab.mentors.exception.ExceptionCode;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.payment.config.TossPaymentConfig;
import kr.nomadlab.mentors.payment.dto.PaymentDto;
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
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    private final ModelMapper modelMapper;
    private final PaymentMapper paymentMapper;
    private final TossPaymentConfig tossPaymentConfig;
    private final MemberMapper memberMapper;

    @Override
    public PaymentDto paymentReqRegister(MemberSecurityDTO memberSecurityDTO, PaymentPAO paymentPAO) {
        PaymentDto paymentDto = PaymentDto.builder()
                .orderId(UUID.randomUUID().toString())
                .payType(paymentPAO.getPayType())
                .amount(paymentPAO.getAmount())
                .orderId(UUID.randomUUID().toString())
                .orderName(paymentPAO.getOrderName())
                .mno(memberSecurityDTO.getMno())
                .memberName(memberSecurityDTO.getMemberName())
                .memberId(memberSecurityDTO.getMemberId())
                .build();
        PaymentVO paymentVO = modelMapper.map(paymentDto, PaymentVO.class);
        log.info(paymentVO);
        paymentMapper.paymentReqInsert(paymentVO);

        return paymentDto;
    }

    @Override
    public PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount) {
        PaymentVO paymentVO = verifyPayment(orderId, amount);

        //최종 결제 승인 요청
        PaymentSuccessDto paymentSuccessDto = requestPaymentAccept(paymentKey, orderId, amount);

        PaymentDto paymentDto = modelMapper.map(paymentVO, PaymentDto.class);
        paymentDto.setPaymentKey(paymentKey);
        paymentDto.setPaySuccessYN(true);
        Long mno = paymentVO.getMno();
        int coin = (int)((amount)/1000);
        memberMapper.updateCoin(mno,coin);

        //성공시 DB업데이트
        PaymentVO paymentVOUp = modelMapper.map(paymentDto, PaymentVO.class);
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
                PaymentDto paymentDto = modelMapper.map(paymentVO, PaymentDto.class);
                paymentDto.setFailReason(message);
                paymentDto.setPaySuccessYN(false);
                paymentDto.setPayFailYN(true);
                PaymentVO resultPaymentVO = modelMapper.map(paymentDto, PaymentVO.class);

                paymentMapper.update(resultPaymentVO);
            }

            paymentFailDto = PaymentFailDto.builder()
                    .errorCode(code)
                    .errorMessage(message)
                    .orderId(orderId).build();
        }

        return paymentFailDto;
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
