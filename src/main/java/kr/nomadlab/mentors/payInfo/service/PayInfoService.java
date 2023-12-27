package kr.nomadlab.mentors.payInfo.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;
import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;

import java.util.List;

public interface PayInfoService {
    Long savePayInfo(Long mno, PayInfoDto payInfoDto);
    void scheduleEvent();

    PageResponseDTO<PayInfoDto> getPayInfo(Long mno, PageRequestDTO pageRequestDTO);
}
