package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminDTO;
import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;

import java.util.List;

public interface AdminService {

    void add(AdminDTO adminDTO);

    AdminVO getMemberId(String adminId);

    List<MentorApplyDTO> getApplyList();

    List<ExchangeDto> adminExchangeSearch(AdminExSearchDTO adminExSearchDTO);

    List<ExchangeDto> adminExchangedSearch(AdminExSearchDTO adminExSearchDTO);

    ExchangeDto adminExchange(Long exNo);

    void completeExchange(Long exNo);
}
