package kr.nomadlab.mentors.member.mapper;

import kr.nomadlab.mentors.member.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void addMember(MemberVO memberVO); // 회원 추가
    void addMemberRole(String memberId, Integer role_set); // 회원 롤 입력

    MemberVO getMemberId(String memberId);  // 회원 이메일 정보 가져오기

    MemberVO getMemberNickname(String nickname); // 닉네임 중복 확인

    void updateMember(String passwd, String nickname, String memberId); // 해당 이메일의 비밀번호 닉네임 변경

    List<MemberVO> getMemberList(); // 전체 회원 리스트

    void updatePassword(String passwd, String memberId); // 비밀번호 재설정

    void deleteMember(String memberId);
}
