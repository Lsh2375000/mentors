package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    void addAdmin(AdminVO adminVO); // 관리자 계정 생성

    void addAdminRole(String memberId, Integer role_set); // 관리자 롤 설정

    AdminVO getAdminId(String adminId); // 관리자 로그인시 롤 불러오기

    List<MentorApplyVO> getApplyList();


    List<ExchangeVO> getExSearchAll(AdminExSearchDTO adminExSearchDTO);

    List<ExchangeVO> getExedSearchAll(AdminExSearchDTO adminExSearchDTO);

    ExchangeVO getExchangeInfo(Long exNo);

    void exchangeComplete(Long exNo);
}
