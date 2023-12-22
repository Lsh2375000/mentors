package kr.nomadlab.mentors.member.service;


import kr.nomadlab.mentors.member.domain.MenteeVO;
import kr.nomadlab.mentors.member.dto.MemberDTO;
import kr.nomadlab.mentors.member.dto.MenteeDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import kr.nomadlab.mentors.member.mapper.MenteeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Log4j2
public class MenteeServiceImpl implements MenteeService{

    private final MenteeMapper menteeMapper;
    private final ModelMapper modelMapper;
    private final MemberMapper memberMapper;

    @Override
    public void add(MenteeDTO menteeDTO) {
        log.info("service add()...");
        log.info(menteeDTO);
        MenteeVO menteeVO = modelMapper.map(menteeDTO, MenteeVO.class);
        Integer role_set = 1; // ROLE_MENTEE로 저장
        memberMapper.addMemberRole(menteeVO.getMemberId(), role_set);

        menteeMapper.insert(menteeVO);
    }

    @Override
    public List<MenteeDTO> getAll() {
        log.info("service getAll()...");

        List<MenteeVO> menteeVOList = menteeMapper.selectAll();
        List<MenteeDTO> menteeDTOList = new ArrayList<>();
        menteeVOList.forEach(menteeVO -> menteeDTOList.add(modelMapper.map(menteeVO, MenteeDTO.class)));
        return menteeDTOList;
    }



    @Override
    public MenteeDTO getOne(String memberId) {
        log.info("service getOne()...");
        MenteeVO menteeVO = menteeMapper.selectOne(memberId);
        MenteeDTO menteeDTO = modelMapper.map(menteeVO, MenteeDTO.class);
        return menteeDTO;
    }


    @Override
    public void modify(MenteeDTO menteeDTO) {
        log.info("service modify()...");

        MenteeVO menteeVO = modelMapper.map(menteeDTO, MenteeVO.class);
        menteeMapper.update(menteeVO);
    }



    @Override
    public void remove(String memberId) {
        log.info("service remove()....");
        menteeMapper.delete(memberId);
    }

}
