package kr.nomadlab.mentors.member.service;

import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public void add(MemberDTO memberJoinDTO) { // 회원 가입

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberJoinDTO.setPasswd(passwordEncoder.encode(memberJoinDTO.getPasswd()));
        MemberVO memberVO = modelMapper.map(memberJoinDTO, MemberVO.class);

        log.info("memberVO : " + memberVO);

        memberMapper.addMember(memberVO);
    }
    // --------------------------------------------------------------------------------
    // 해당 이메일 조회
    @Override
    public MemberVO getMemberId(String memberId) {
        return memberMapper.getMemberId(memberId);
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
    public void modifyMember(MemberDTO memberJoinDTO) {

        MemberVO memberVO = modelMapper.map(memberJoinDTO, MemberVO.class);
        log.info(memberVO);
        memberMapper.updateMember(memberVO.getPasswd(), memberVO.getNickname(), memberVO.getMemberId());
    }

    @Override
    public void removeMember(String memberId) {
        memberMapper.deleteMember(memberId);
    }
    // --------------------------------------------------------------------------------
    //


}
