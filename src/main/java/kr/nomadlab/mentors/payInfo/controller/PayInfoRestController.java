package kr.nomadlab.mentors.payInfo.controller;

import kr.nomadlab.mentors.member.dto.MemberSecurityDTO;
import kr.nomadlab.mentors.member.service.MemberService;
import kr.nomadlab.mentors.payInfo.dto.PayInfoDTO;
import kr.nomadlab.mentors.payInfo.service.PayInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payInfo")
@Log4j2
@RequiredArgsConstructor // 의존성 주입 위해
public class PayInfoRestController {
    private final PayInfoService payInfoService;
    private final MemberService memberService;
    private final UserDetailsService userDetailsService;

    @PutMapping(value = "/save/{mbNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> savePayInfo(@RequestBody PayInfoDTO payInfoDTO, @AuthenticationPrincipal MemberSecurityDTO member, @PathVariable("mbNo") Long mbNo){
        log.info("this is payInfo save");
        log.info(payInfoDTO);

        //member에 코인 - 해주기
        payInfoService.savePayInfo(member.getMno(), payInfoDTO);
        if(payInfoDTO.getPrice() == 0) {
            memberService.payCoin(member.getMno(), payInfoDTO.getPrice());
        }else if(payInfoDTO.getPrice() != 0){
            memberService.payCoin(member.getMno(), -payInfoDTO.getPrice());
        }
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
