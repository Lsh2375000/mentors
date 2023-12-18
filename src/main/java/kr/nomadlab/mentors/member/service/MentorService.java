package kr.nomadlab.mentors.member.service;

import kr.nomadlab.mentors.member.dto.MentorDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MentorService {

    void add(MentorDTO mentorDTO, List<MultipartFile> files); // 멘토 회원가입

    List<MentorDTO> getAll(); // 멘토 목록

    MentorDTO getOne(String memberId); // 해당 멘토 정보

    void modify(MentorDTO mentorDTO, List<MultipartFile> files); // 멘토 정보 수정

    void remove(String memberId); // 멘토 회원 탈퇴

    void modifyPw(String passwd, String memberId);


}
