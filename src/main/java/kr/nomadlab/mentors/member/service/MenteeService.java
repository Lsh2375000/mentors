package kr.nomadlab.mentors.member.service;

import kr.nomadlab.mentors.member.dto.MenteeDTO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;

import java.util.List;

public interface MenteeService {

    void add(MenteeDTO menteeDTO); // 멘티 회원가입

    List<MenteeDTO> getAll(); // 멘티 목록

    MenteeDTO getOne(String memberId); // 해당 아이디 멘티 회원 정보

    MenteeDTO getOneByMno(Long mno); // 해당 mno의 멘티 회원 정보
    void modify(MenteeDTO menteeDTO); // 멘티 정보 수정

    void remove(String memberId); // 멘티 회원탈퇴

    void introWrite(String intro, Long mno); // 멘티 자기소개 작성 및 수정

    Long getApplyMno(Long mno);
}
