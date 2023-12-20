package kr.nomadlab.mentors.admin.mapper;

import kr.nomadlab.mentors.admin.domain.AdminVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {
    void addAdmin(AdminVO adminVO); // 관리자 계정 생성

    void addAdminRole(String adminId, Integer role_set); // 관리자 롤 설정

    AdminVO getAdminId(String adminId); // 관리자 로그인시 롤 불러오기

}
