package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminDTO;
import kr.nomadlab.mentors.admin.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminUserMapper adminMapper;
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
}
