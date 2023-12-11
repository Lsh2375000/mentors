package kr.nomadlab.progrow_project.member.service;

import kr.nomadlab.progrow_project.member.dto.MemberJoinDTO;
import kr.nomadlab.progrow_project.member.mapper.SMemberMapper;
import kr.nomadlab.progrow_project.member.domain.SMemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SMemberServiceImpl implements SMemberService {

    private final SMemberMapper sMemberMapper;
    private final ModelMapper modelMapper;

    @Override
    public void add(MemberJoinDTO memberJoinDTO) { // 회원 가입

        SMemberVO memberVO = modelMapper.map(memberJoinDTO, SMemberVO.class);

        log.info("memberVO : " + memberVO);

        sMemberMapper.addMember(memberVO);
        Integer role_set = 0; // 뷰로 회원가입할 때 자동으로 ROLE_USER 로 등록
        sMemberMapper.addMemberRole(memberJoinDTO.getMid(), role_set);

    }
    // --------------------------------------------------------------------------------
    // 해당 이메일 조회
    @Override
    public SMemberVO getMemberId(String mid) {
        return sMemberMapper.getMemberId(mid);
    }
    // --------------------------------------------------------------------------------
    // 닉네임 중복 확인
    @Override
    public SMemberVO getMemberNickname(String nickname) {
        return sMemberMapper.getMemberNickname(nickname);
    }
    // --------------------------------------------------------------------------------
    // 해당 회원의 비밀번호 변경
    @Override
    public void modifyPassword(String mpw, String mid) { // 회원 비밀번호 수정

        sMemberMapper.updatePassword(mpw, mid);
    }

    @Override
    public void modifyMember(MemberJoinDTO memberJoinDTO) {

        SMemberVO sMemberVO = modelMapper.map(memberJoinDTO, SMemberVO.class);
        log.info(sMemberVO);
        sMemberMapper.updateMember(sMemberVO.getMpw(), sMemberVO.getNickname(), sMemberVO.getMid());
    }

    @Override
    public void removeMember(String mid) {
        sMemberMapper.deleteMember(mid);
    }
    // --------------------------------------------------------------------------------
    //


}
