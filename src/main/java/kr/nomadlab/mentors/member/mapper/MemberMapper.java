package kr.nomadlab.mentors.member.mapper;

import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.payInfo.vo.PayInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Long addMember(MemberVO memberVO); // 회원 추가
    void addMemberRole(String memberId, Integer role_set); // 회원 롤 입력

    MemberVO getOne(String memberId); // 해당 아이디의 회원 정보를 가져옴

    MemberVO getMemberId(String memberId);  // 회원 이메일 정보 가져오기

    MemberVO getMemberNickname(String nickname); // 해당 닉네임을 가진 유저의 정보를 가져옴

    void updateMember(String passwd, String nickname, String region, String memberName, String memberId); // 해당 이메일의 비밀번호 닉네임 변경

    List<MemberVO> getMemberList(); // 전체 회원 리스트(관리자 전용)

    void updatePassword(String passwd, String memberId); // 비밀번호 재설정

    void deleteMember(String memberId); // 탈퇴한 회원 정보 삭제
    void updateIsDel(String memberId); // 회원 탈퇴(논리적 삭제)
    void updateCoin(Long mno, int coin); //멤버 코인 구매, 멘토링 환불
    void updateMemberRole(String memberId); // 회원 멤버롤 변경(관리자 전용)

    void addMentorApply(MentorApplyVO mentorApplyVO); // 멘티의 멘토 신청

    int getMemberRole(String memberId); // 해당 아이디의 ROLE을 들고옴

    void insertMentorCoin(PayInfoVO payInfoVO); // 멘토링 종료후 멘토한테 코인 지급

    void exchangeCoin(Long mno);

    String getNickName(Long mno);
}
