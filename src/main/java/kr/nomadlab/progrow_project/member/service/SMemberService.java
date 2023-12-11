package kr.nomadlab.progrow_project.member.service;

import kr.nomadlab.progrow_project.member.domain.SMemberVO;
import kr.nomadlab.progrow_project.member.dto.MemberJoinDTO;

public interface SMemberService {

    void add(MemberJoinDTO memberJoinDTO); // 회원가입

    SMemberVO getMemberId(String mid); // 회원 정보 조회

    SMemberVO getMemberNickname(String nickname); // 닉네임 중복 확인

    void modifyPassword(String mpw, String mid); // 비밀번호 변경

    void modifyMember(MemberJoinDTO memberJoinDTO);

    void removeMember(String mid);

}
