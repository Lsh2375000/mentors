package kr.nomadlab.mentors.payInfo.service;

import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;
import kr.nomadlab.mentors.payInfo.mapper.PayInfoMapper;
import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PayInfoServiceImpl implements PayInfoService{
    private final ModelMapper modelMapper;

    private final PayInfoMapper payInfoMapper;

    @Override
    public void savePayInfo(Long mno, PayInfoDto payInfoDto) {
        PayInfoVO payInfoVO = PayInfoVO.builder()
                .mbNo(payInfoDto.getMbNo())
                .mentorMno(payInfoDto.getMentorMno())
                .menteeMno(mno)
                .price(payInfoDto.getPrice())
                .build();

        payInfoMapper.insertPayInfo(payInfoVO);
    }
}
