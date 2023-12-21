package kr.nomadlab.mentors.payInfo.service;

import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;

public interface PayInfoService {
    void savePayInfo(Long mno, PayInfoDto payInfoDto);
}
