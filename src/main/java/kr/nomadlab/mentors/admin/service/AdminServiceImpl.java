package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminDTO;
import kr.nomadlab.mentors.admin.dto.AdminExSearchDTO;
import kr.nomadlab.mentors.admin.mapper.AdminMapper;
import kr.nomadlab.mentors.exChange.dto.ExchangeDto;
import kr.nomadlab.mentors.exChange.vo.ExchangeVO;
import kr.nomadlab.mentors.member.domain.MentorApplyVO;
import kr.nomadlab.mentors.member.dto.MentorApplyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMapper adminMapper;
    private final ModelMapper modelMapper;

    @Override
    public void add(AdminDTO adminDTO) { // 관리자 계정 생성
        log.info("adminDTO : " + adminDTO);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        adminDTO.setPasswd(passwordEncoder.encode(adminDTO.getPasswd()));
        AdminVO adminVO =  modelMapper.map(adminDTO, AdminVO.class);

        log.info("adminVO : " + adminVO);

        adminMapper.addAdmin(adminVO);

    }

    @Override
    public AdminVO getMemberId(String adminId) { // 관리자 정보 불러오기
        return adminMapper.getAdminId(adminId);
    }
    @Override
    public List<MentorApplyDTO> getApplyList() {
        List<MentorApplyVO> voList = adminMapper.getApplyList();
        log.info(voList);
        List<MentorApplyDTO> dtoList = new ArrayList<>();

        voList.forEach(mentorApplyVO -> {
            MentorApplyDTO mentorApplyDTO = modelMapper.map(mentorApplyVO, MentorApplyDTO.class);
            dtoList.add(mentorApplyDTO);
        });

        return dtoList;
    }

    @Override
    public List<ExchangeDto> adminExchangeSearch(AdminExSearchDTO adminExSearchDTO) {
        List<ExchangeVO> exchangeVOList = adminMapper.getExSearchAll(adminExSearchDTO);
        List<ExchangeDto> exchangeDtoList = new ArrayList<>();
        exchangeVOList.forEach(exchangeVO -> {
            ExchangeDto exchangeDto = modelMapper.map(exchangeVO, ExchangeDto.class);
            exchangeDtoList.add(exchangeDto);
        });

        return exchangeDtoList;
    }

    @Override
    public List<ExchangeDto> adminExchangedSearch(AdminExSearchDTO adminExSearchDTO) {
        List<ExchangeVO> exchangeVOList = adminMapper.getExedSearchAll(adminExSearchDTO);
        List<ExchangeDto> exchangeDtoList = new ArrayList<>();
        exchangeVOList.forEach(exchangeVO -> {
            ExchangeDto exchangeDto = modelMapper.map(exchangeVO, ExchangeDto.class);
            exchangeDtoList.add(exchangeDto);
        });

        return exchangeDtoList;
    }

    @Override
    public ExchangeDto adminExchange(Long exNo) {
        ExchangeVO exchangeVO = adminMapper.getExchangeInfo(exNo);
        ExchangeDto exchangeDto = modelMapper.map(exchangeVO, ExchangeDto.class);

        return exchangeDto;
    }

    @Override
    public void completeExchange(Long exNo) {
        adminMapper.exchangeComplete(exNo);
    }

    @Override
    public MentorApplyDTO getApplyOne(Long mno) {
        MentorApplyVO mentorApplyVO = adminMapper.getApplyOne(mno);
        MentorApplyDTO mentorApplyDTO = modelMapper.map(mentorApplyVO, MentorApplyDTO.class);
        return mentorApplyDTO;
    }

    @Override
    public void removeApplyOne(Long mno) {
        adminMapper.removeApplyOne(mno);
    }

    @Override
    public void changeRole(String memberId) { // 멘티 회원 ROLE 변경
        adminMapper.changeRole(memberId);
    }
}
