package kr.nomadlab.mentors.member.service;


import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {

    Long add(MemberDTO memberDTO); // 회원가입

    MemberVO getMemberId(String memberId); // 회원 정보 조회

    MemberDTO getOne(String memberId);
    MemberVO getMemberNickname(String nickname); // 닉네임 중복 확인
    void modifyPassword(String passwd, String memberId); // 비밀번호 변경

    void modifyMember(MemberDTO memberDTO); // 회원 정보 수정

    void removeMember(String memberId); // 탈퇴한 회원 정보 삭제

    void updateMemberIsDel(String memberId); // 회원 탈퇴시 논리적 삭제

    void addMentorApply(MentorApplyDTO mentorApplyDTO, List<MultipartFile> files);

    MemberDTO getProfileNickname(String nickname);

    int getMemberRole(String memberId);

    void payCoin(Long mno, int price); //코인 결제

    void exchangeCoin(Long mno);
}
