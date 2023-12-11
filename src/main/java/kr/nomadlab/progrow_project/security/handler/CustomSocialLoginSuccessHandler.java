package kr.nomadlab.progrow_project.security.handler;

import kr.nomadlab.progrow_project.member.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("---------------------------------------------------");
        log.info("CustomSocialLoginSuccessHandler onAuthenticationSuccess................");
        log.info(authentication.getPrincipal());

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();

        String encodedPw = memberSecurityDTO.getMpw();
        // 예) 소셜 로그인이고 회원의 패스워드가 1111 이라면
        if (memberSecurityDTO.isSocial()
                && memberSecurityDTO.getMpw().equals("1111")) {
            log.info("Should Register");

            log.info("Redirect to Social Register");
            response.sendRedirect("/member/socialRegister");

        } else {
            response.sendRedirect("/");
        }

    }
}
