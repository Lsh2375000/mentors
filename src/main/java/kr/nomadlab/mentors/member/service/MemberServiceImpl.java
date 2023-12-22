package kr.nomadlab.mentors.member.service;

import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.member.domain.MentorVO;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import kr.nomadlab.mentors.member.dto.MentorDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Value("${kr.nomadlab.upload.path}")
    private String uploadPath;

    @Override
    public Long add(MemberDTO memberDTO) { // 회원 가입

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPasswd(passwordEncoder.encode(memberDTO.getPasswd()));
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);


        memberMapper.addMember(memberVO);
        log.info("memberVO : " + memberVO);

        return memberVO.getMno();
    }
    // --------------------------------------------------------------------------------
    // 해당 이메일 조회
    @Override
    public MemberVO getMemberId(String memberId) {
        return memberMapper.getMemberId(memberId);
    }

    @Override
    public MemberDTO getOne(String memberId) {
        MemberVO memberVO = memberMapper.getOne(memberId);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    // --------------------------------------------------------------------------------
    // 닉네임 중복 확인
    @Override
    public MemberVO getMemberNickname(String nickname) {
        return memberMapper.getMemberNickname(nickname);
    }
    // --------------------------------------------------------------------------------
    // 해당 회원의 비밀번호 변경
    @Override
    public void modifyPassword(String passwd, String memberId) { // 회원 비밀번호 수정

        memberMapper.updatePassword(passwd, memberId);
    }

    @Override
    public void modifyMember(MemberDTO memberDTO) { // 회원 정보 수정

        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info(memberVO);
        memberMapper.updateMember(memberVO.getPasswd(), memberVO.getNickname(), memberVO.getMemberId());
    }

    @Override
    public void removeMember(String memberId) { // 탈퇴 회원 정보 삭제
        memberMapper.deleteMember(memberId);
    }

    @Override
    public void updateMemberIsDel(String memberId) { // 회원 탈퇴(논리적 삭제)
        memberMapper.updateIsDel(memberId);
    }

    @Override
    public void addMentorApply(MentorApplyDTO mentorApplyDTO, List<MultipartFile> files) { // 멘토 신청
        log.info("mentorApplyDTO : " + mentorApplyDTO);
        if (files.size() > 0) {
            StringBuilder str = new StringBuilder();
            for(MultipartFile file : files) {
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + file.getOriginalFilename());
                str.append(uuid).append("_").append(file.getOriginalFilename()).append("|");
                try {
                    file.transferTo(savePath);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            mentorApplyDTO.setFileNames(str.toString());
            log.info("입력된 첨부파일 : " + str.toString());
        }
        MentorApplyVO mentorApplyVO = modelMapper.map(mentorApplyDTO, MentorApplyVO.class);
        log.info("mentorApplyVO : " + mentorApplyVO);
        memberMapper.addMentorApply(mentorApplyVO);
    }

    @Override
    public MemberDTO getProfileNickname(String nickname) {
        MemberVO memberVO = memberMapper.getMemberNickname(nickname);
        log.info(memberVO);

        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        log.info(memberDTO);

        return memberDTO;
    }
    @Override
    public void payCoin(Long mno, int price) {
        memberMapper.updateCoin(mno, price);
    }

    @Override
    public int getMemberRole(String memberId) {
        return memberMapper.getMemberRole(memberId);
    }
    // --------------------------------------------------------------------------------
    //


}
