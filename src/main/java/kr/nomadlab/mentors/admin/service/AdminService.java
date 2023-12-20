package kr.nomadlab.mentors.admin.service;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import kr.nomadlab.mentors.admin.dto.AdminDTO;

public interface AdminService {

    void add(AdminDTO adminDTO);

    AdminVO getMemberId(String adminId);


}
