package kr.nomadlab.mentors.member.service;


import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.dto.MemberDTO;

public interface MemberService {

    void add(MemberDTO memberJoinDTO); // 회원가입

    MemberVO getMemberId(String memberId); // 회원 정보 조회

    MemberVO getMemberNickname(String nickname); // 닉네임 중복 확인

    void modifyPassword(String passwd, String memberId); // 비밀번호 변경

    void modifyMember(MemberDTO memberJoinDTO);

    void removeMember(String memberId);

}
