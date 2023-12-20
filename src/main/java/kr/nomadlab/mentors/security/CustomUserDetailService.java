package kr.nomadlab.mentors.security;

import kr.nomadlab.mentors.member.domain.MemberRole;
import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
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
    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: "+username);



        MemberVO member = memberMapper.getMemberId(username);


        Set<MemberRole> roles = member.getRoleSet();


        Set<MemberRole> newRoles = new HashSet<>();
        if ((Integer)roles.toArray()[0] == 0) {
            MemberRole memberRole = MemberRole.values()[0];
            newRoles.add(memberRole);

        } else if ((Integer) roles.toArray()[0] == 1) {
            MemberRole memberRole = MemberRole.values()[1];
            newRoles.add(memberRole);
        }
        member.setRoleSet(newRoles);

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(member);

        if(member == null || member.isDel() == true) { // 해당 이메일를 가진 사용자가 없거나 del이 ture로 바뀌거나
            throw new UsernameNotFoundException("username not found...");
        }

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        log.info( member.getRoleSet().stream()
                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                .collect(Collectors.toList()));

        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMno(), member.getMemberId(), member.getPasswd(), member.isDel(), false,  member.getNickname(), member.getMemberName(), member.getCoin(),
                member.getRegion(), member.getRoleSet().stream()
                        .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                        .collect(Collectors.toList())
        );
        log.info("memberSecurityDTO : " + memberSecurityDTO);
        return memberSecurityDTO;
    }




}