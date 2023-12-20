//package kr.nomadlab.mentors.admin.security;
//
//import kr.nomadlab.mentors.admin.domain.AdminRole;
//import kr.nomadlab.mentors.admin.domain.AdminVO;
//import kr.nomadlab.mentors.admin.dto.AdminSecurityDTO;
//import kr.nomadlab.mentors.admin.mapper.AdminUserMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Log4j2
//public class CustomAdminDetailService implements UserDetailsService {
//    private final AdminUserMapper adminUserMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
//        log.info("loadUserByUsername: " + adminId);
//
//        AdminVO admin = adminUserMapper.getAdminId(adminId);
//
//
//        Set<AdminRole> roles = admin.getRoleSet();
//
//
//        Set<AdminRole> newRoles = new HashSet<>();
//        if ((Integer)roles.toArray()[0] == 0) { // 회원 관리자
//            AdminRole adminRole = AdminRole.values()[0];
//            newRoles.add(adminRole);
//
//        } else if ((Integer) roles.toArray()[0] == 1) { // 결제 관리자
//            AdminRole adminRole = AdminRole.values()[1];
//            newRoles.add(adminRole);
//
//        } else if ((Integer) roles.toArray()[0] == 2) { // 게시판 관리자
//            AdminRole adminRole = AdminRole.values()[2];
//            newRoles.add(adminRole);
//        }
//        admin.setRoleSet(newRoles);
//
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        log.info(admin);
//
//        if(admin == null) { // 해당 관리자 아이디 사용자가 없다면
//            throw new UsernameNotFoundException("username not found...");
//        }
//
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//
//        log.info(admin.getRoleSet().stream()
//                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
//                .collect(Collectors.toList()));
//
//        AdminSecurityDTO adminSecurityDTO = new AdminSecurityDTO(
//                admin.getAno(), admin.getAdminId(), admin.getPasswd(),
//                admin.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
//                .collect(Collectors.toList())
//        );
//        log.info("adminSecurityDTO : " + adminSecurityDTO);
//        return adminSecurityDTO;
//    }
//}
