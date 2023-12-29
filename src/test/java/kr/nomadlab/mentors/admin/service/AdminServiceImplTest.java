package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.dto.AdminDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @Test
    void add() {
        AdminDTO adminDTO = AdminDTO.builder()
                .adminId("admin@admin")
                .passwd("admin1234")
                .build();
        adminService.add(adminDTO);
    }
}