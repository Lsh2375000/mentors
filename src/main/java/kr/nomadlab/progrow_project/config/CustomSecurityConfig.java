package kr.nomadlab.progrow_project.config;

import kr.nomadlab.progrow_project.security.CustomUserDetailService;
import kr.nomadlab.progrow_project.security.handler.Custom403Handler;
import kr.nomadlab.progrow_project.security.handler.CustomAuthFailureHandler;
import kr.nomadlab.progrow_project.security.handler.CustomSocialLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;



import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
    //주입 필요
    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("-----------------Configuration-------------------");

         // 커스텀 로그인 페이지
         http.formLogin().loginPage("/member/login");
         // CRSF 토큰 비활성화
         http.csrf().disable();

         http.rememberMe()
                 .key("12345678")
                 .tokenRepository(persistentTokenRepository())
                 .userDetailsService(customUserDetailService)
                 .tokenValiditySeconds(60 * 60 * 24 * 30); // 유효기간 30일

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http.oauth2Login().loginPage("/member/login").successHandler(authenticationSuccessHandler());


        return http.build();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    @Bean
    public WebSecurityCustomizer webClientCustomizer() {
        log.info("---------------------------Web Configure--------------------");

        // 정적 파일 경로에 시큐리티 적용을 안함.
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthFailureHandler();
    }


}
