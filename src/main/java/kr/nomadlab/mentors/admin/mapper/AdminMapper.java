package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    void addAdmin(AdminVO adminVO); // 관리자 계정 생성

    void addAdminRole(@Param("adminId") String adminId, @Param("role_set") Integer role_set); // 관리자 롤 설정

    AdminVO getAdminId(String adminId); // 관리자 로그인시 롤 불러오기

    List<MentorApplyVO> getApplyList(); // 관리자 페이지에서 멘토 신청 목록 출력

    MentorApplyVO getApplyOne(Long mno); // 해당 mno의 멘토 추가정보 가져옴

    void removeApplyOne(Long mno); // 멘토로 변경후 해당 mno의 멘토 신청 정보 삭제

    void changeRole(String memberId);

    List<ExchangeVO> getExSearchAll(AdminExSearchDTO adminExSearchDTO);

    List<ExchangeVO> getExedSearchAll(AdminExSearchDTO adminExSearchDTO);

    ExchangeVO getExchangeInfo(Long exNo);

    void exchangeComplete(Long exNo);

    void exPaySuccess(Long payInfoNo); //결제 저장소 환전 완료시 success true
}
