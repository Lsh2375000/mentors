package kr.nomadlab.mentors.payInfo.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.main.mapper.MainMapper;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;
import kr.nomadlab.mentors.payInfo.mapper.PayInfoMapper;
import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import kr.nomadlab.mentors.payment.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PayInfoServiceImpl implements PayInfoService{
    private final ModelMapper modelMapper;

    private final PayInfoMapper payInfoMapper;
    private final MemberMapper memberMapper;

    private final MainMapper mainMapper;

    @Override
    public Long savePayInfo(Long mno, PayInfoDto payInfoDto) {
        PayInfoVO payInfoVO = PayInfoVO.builder()
                .mbNo(payInfoDto.getMbNo())
                .mentorMno(payInfoDto.getMentorMno())
                .menteeMno(mno)
                .completeDate(payInfoDto.getCompleteDate())
                .price(payInfoDto.getPrice())
                .build();

        payInfoMapper.insertPayInfo(payInfoVO);
        log.info("this is payInfo" + payInfoVO.getPayInfoNo());

        return payInfoVO.getPayInfoNo();
    }

    //00시 00분 00초에 전날 완료된 강의 멘토에게 비타민 보내기
    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void scheduleEvent() {
        log.info("this is schedule");
        List<PayInfoVO> payInfoVOList = payInfoMapper.checkDate();
        log.info(payInfoVOList);
        payInfoVOList.forEach(payInfoVO -> {
            payInfoMapper.updateIsComplete(payInfoVO.getMbNo());
            mainMapper.falseIsMentoring(payInfoVO.getMentorMno());
            memberMapper.insertMentorCoin(payInfoVO);
        });
    }

    @Override
    public PageResponseDTO<PayInfoDto> getPayInfo(Long mno, PageRequestDTO pageRequestDTO) {
        List<PayInfoVO> payInfoVOList = payInfoMapper.getPayInfo(mno, pageRequestDTO.getSkip(), pageRequestDTO.getSize());
        List<PayInfoDto> payInfoDtOList = new ArrayList<>();
        if(payInfoVOList != null) {
            payInfoVOList.forEach(payInfoVO -> {
                if (payInfoVO.getPrice() != 0) {
                    log.info(payInfoVO);
                    PayInfoDto payInfoDto = modelMapper.map(payInfoVO, PayInfoDto.class);
                    payInfoDtOList.add(payInfoDto);
                }
            });
        }
        int total = payInfoMapper.getCount(mno);

        return PageResponseDTO.<PayInfoDto>withAll()
                .dtoList(payInfoDtOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
