package kr.nomadlab.mentors.security;

import kr.nomadlab.mentors.member.domain.MemberRole;
import kr.nomadlab.mentors.member.domain.MemberVO;
import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("userRequest....");
        log.info(userRequest);

        log.info("oauth2 user...");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME: " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String memberId = null;
        switch (clientName) {
            case "kakao":
                memberId = getKakaoEmail(paramMap);
                break;
        }

        log.info("================");
        log.info(memberId);
        log.info("================");

//        paramMap.forEach((k, v) -> {
//            log.info("--------");
//            log.info(k + ":" + v);
//        });


        return generateDTO(memberId, paramMap);
    }


    private String getKakaoEmail(Map<String, Object> paramMap) {
        log.info("KAKAO.....................");

        Object value = paramMap.get("kakao_account");
        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");

        log.info("email...." + email);
        return email;
    }

    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MemberVO member = memberMapper.getMemberId(email);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(member);
        // 데이터베이스에 해당 이메일의 사용자가 없다면
        if(member == null) {
            log.info("social member");

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    0L, email, "1111", false, true, "", "", 0, "",
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_"))
            );
            memberSecurityDTO.setProps(params);
            return memberSecurityDTO;
        }
        else {
            log.info("social login");
            Set<MemberRole> roles = member.getRoleSet();
            Set<MemberRole> newRoles = new HashSet<>();
            for(int i=0; i<roles.size(); i++){
                MemberRole test = MemberRole.values()[i];
                newRoles.add(test);
            }
            member.setRoleSet(newRoles);
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    member.getMno(),
                    member.getMemberId(),
                    member.getPasswd(),
                    member.isDel(),
                    true,
                    member.getNickname(),
                    member.getMemberName(),
                    member.getCoin(),
                    member.getRegion(),
                    member.getRoleSet().stream()
                            .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                            .collect(Collectors.toList())
            );
            return memberSecurityDTO;
        }
    }
}
