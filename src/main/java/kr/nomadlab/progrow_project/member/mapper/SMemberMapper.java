package kr.nomadlab.progrow_project.member.mapper;

import kr.nomadlab.progrow_project.member.domain.SMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SMemberMapper {

    void addMember(SMemberVO memberVO); // 회원 추가
    void addMemberRole(String member_mid, Integer role_set); // 회원 롤 입력

    SMemberVO getMemberId(String mid);  // 회원 이메일 정보 가져오기

    SMemberVO getMemberNickname(String nickname); // 닉네임 중복 확인

    void updateMember(String mpw, String nickname, String mid); // 해당 이메일의 비밀번호 닉네임 변경

    List<SMemberVO> getMemberList(); // 전체 회원 리스트

    void updatePassword(String mpw, String mid); // 비밀번호 재설정

    void deleteMember(String mid);
}
