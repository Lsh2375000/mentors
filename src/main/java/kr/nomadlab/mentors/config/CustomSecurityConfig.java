package kr.nomadlab.mentors.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.nomadlab.mentors.admin.dto.VisitorDTO;
import kr.nomadlab.mentors.admin.service.VisitorService;
import kr.nomadlab.mentors.security.CustomUserDetailService;
import kr.nomadlab.mentors.security.handler.Custom403Handler;
import kr.nomadlab.mentors.security.handler.CustomAuthFailureHandler;
import kr.nomadlab.mentors.security.handler.CustomSocialLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class CustomSecurityConfig {
    //주입 필요
    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainMemberAdmin(HttpSecurity httpSecurity) throws Exception {
        log.info("---------------------Configuration MEMBER ADMIN-----------------------");
        httpSecurity.securityMatcher("/admin/**");

        httpSecurity.authorizeHttpRequests(requests -> {
           requests.requestMatchers("/admin/login").permitAll();
           requests.anyRequest().hasRole("ADMIN");

        });
        httpSecurity.formLogin(config -> {
            config.loginPage("/admin/login");
            config.defaultSuccessUrl("/admin/stats");
        })
        .logout(logoutConfig -> {
            logoutConfig
                    .logoutUrl("/admin/logout")
                    .logoutSuccessHandler(
                            ((request, response, authentication) -> {
                                log.info("로그아웃 성공");
                                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                response.getWriter().println("로그아웃 성공!!");
                                response.sendRedirect("/admin/login");
                            })
                    ).logoutSuccessUrl("admin/login");
        });
        httpSecurity.csrf(crsf -> crsf.disable());


        return httpSecurity.build();
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        log.info("-----------------User Configuration-------------------");

         // 커스텀 로그인 페이지
         http.formLogin(login -> login
                 .loginPage("/member/login")
                 .defaultSuccessUrl("/")
         )        .logout(logoutConfig -> {
             logoutConfig
                     .logoutUrl("/logout")
                     .logoutSuccessHandler(
                             ((request, response, authentication) -> {
                                 log.info("로그아웃 성공");
                                 response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                                 response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                 response.getWriter().println("로그아웃 성공!!");
                                 response.sendRedirect("/");
                             })
                     );
         });;
         // CRSF 토큰 비활성화
         http.csrf(AbstractHttpConfigurer::disable);
         http.headers(header -> header
                 .frameOptions()
                 .sameOrigin()
                 );

         http.rememberMe(remember -> remember
                 .key("12345678")
                 .tokenRepository(persistentTokenRepository())
                 .userDetailsService(customUserDetailService)
                 .tokenValiditySeconds(60 * 60 * 24 * 30)// 유효기간 30일
                 );
        http.exceptionHandling(config -> config
                .accessDeniedHandler(accessDeniedHandler())
        );

        http.oauth2Login(login -> login
                .loginPage("/member/login")
                .successHandler(authenticationSuccessHandler())
        );

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
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
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


