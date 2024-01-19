package kr.nomadlab.mentors.payInfo.service;

import kr.nomadlab.mentors.common.PageRequestDTO;
import kr.nomadlab.mentors.common.PageResponseDTO;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDTO;

public interface PayInfoService {
    Long savePayInfo(Long mno, PayInfoDTO payInfoDto);
    void scheduleEvent();

    PageResponseDTO<PayInfoDTO> getPayInfo(Long mno, PageRequestDTO pageRequestDTO);
}
