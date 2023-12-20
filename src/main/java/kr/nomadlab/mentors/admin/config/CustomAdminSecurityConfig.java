//package kr.nomadlab.mentors.admin.config;
//
//import kr.nomadlab.mentors.admin.security.CustomAdminDetailService;
//import kr.nomadlab.mentors.security.handler.CustomSocialLoginSuccessHandler;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@EnableMethodSecurity
//@Configuration
//@RequiredArgsConstructor
//@Log4j2
//@Order(1)
//public class CustomAdminSecurityConfig {
//
//    private final CustomAdminDetailService customAdminDetailService;
//
//    @Bean
//    public WebSecurityCustomizer webClientCustomizer() {
//        log.info("---------------------------Web Configure--------------------");
//
//        // 정적 파일 경로에 시큐리티 적용을 안함.
//        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
//    }
//
//    @Bean
//    protected SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/login", "/css/**", "/images/**", "/js/**").permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
//                .loginPage("/admin/login")
//                .loginProcessingUrl("/login")
//                .permitAll()
//                .successHandler(authenticationSuccessHandler());
//
//        http
//                .sessionManagement()
//                .invalidSessionUrl("/login")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll();
//
//
//        //CSRF 토큰
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new CustomSocialLoginSuccessHandler(passwordEncoder());
//    }
//}
