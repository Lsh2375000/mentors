package kr.nomadlab.progrow_project.security;

import kr.nomadlab.progrow_project.member.domain.MemberRole;
import kr.nomadlab.progrow_project.member.domain.SMemberVO;
import kr.nomadlab.progrow_project.member.dto.MemberSecurityDTO;
import kr.nomadlab.progrow_project.member.mapper.SMemberMapper;
import kr.nomadlab.progrow_project.member.service.SMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Log4j2
@Service
@RequiredArgsConstructor // 추가
public class CustomUserDetailService implements UserDetailsService {
    private final SMemberService memberService;
    private final SMemberMapper sMemberMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: "+username);

        SMemberVO member = sMemberMapper.getMemberId(username);
        Set<MemberRole> roles = member.getRoleSet();
        Set<MemberRole> newRoles = new HashSet<>();
        for(int i=0; i<roles.size(); i++){
            MemberRole test = MemberRole.values()[i];
            newRoles.add(test);
        }
        member.setRoleSet(newRoles);

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(member);

        if(member == null) { // 해당 이메일를 가진 사용자가 없다면
            throw new UsernameNotFoundException("username not found...");
        }

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        log.info( member.getRoleSet().stream()
                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                .collect(Collectors.toList()));

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMid(), member.getMpw(), member.isDel(), false, member.getType(), member.getNickname(),
                member.getRoleSet().stream()
                        .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                        .collect(Collectors.toList())
        );
        log.info("memberSecurityDTO : " + memberSecurityDTO);
        return memberSecurityDTO;
    }




}