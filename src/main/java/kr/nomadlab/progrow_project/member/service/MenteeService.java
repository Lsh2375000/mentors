package kr.nomadlab.progrow_project.member.service;

import kr.nomadlab.progrow_project.member.dto.MenteeDTO;

import java.util.List;

public interface MenteeService {

    void add(MenteeDTO menteeDTO); // 멘티 회원가입

    List<MenteeDTO> getAll(); // 멘티 목록

    MenteeDTO getOne(String mentee_id); // 해당 멘티 회원

    void modify(MenteeDTO menteeDTO); // 멘티 정보 수정

    void remove(String mentee_id); // 멘티 회원탈퇴

    void modifyPw(String passwd, String mentee_id);

}
