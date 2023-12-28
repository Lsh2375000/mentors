package kr.nomadlab.mentors.payInfo.controller;

import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.service.MemberService;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDto;
import kr.nomadlab.mentors.payInfo.service.PayInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/payInfo")
@Log4j2
@RequiredArgsConstructor // 의존성 주입 위해
public class PayInfoRestController {
    private final PayInfoService payInfoService;
    private final MemberService memberService;
    private final UserDetailsService userDetailsService;

    @PutMapping("/save")
    public ResponseEntity<String> savePayInfo(@AuthenticationPrincipal MemberSecurityDTO member, @RequestBody PayInfoDto payInfoDto){
        log.info("this is payInfo save");
        //member에 코인 - 해주기
        payInfoService.savePayInfo(member.getMno(), payInfoDto);
        memberService.payCoin(member.getMno(), -payInfoDto.getPrice());

        sessionReset(member);

        return ResponseEntity.ok("Request processed successfully");
    }

    private void sessionReset(MemberSecurityDTO memberDTO) {
        // 세션 사용자 정보 업데이트
        UserDetails userDetails = userDetailsService.loadUserByUsername(memberDTO.getMemberId());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, memberDTO.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }
}
