package com.korea.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.korea.project.service.user.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	private UserDe
	private final UserDetailServiceImpl userDetailServiceImpl;
	private final CustomAuthFailureHandler customFailureHandler;
	private final CustomAuthSuccessHandler customSuccessHandler;
	private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailServiceImpl)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    	http
    	.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(authorize -> authorize
        		.requestMatchers("/user").permitAll()
                // /user : 인증만 되면 들어갈 수 있는 주소
                .requestMatchers("/admin/**").hasAnyRole("admin")
                //ADMIN을 가진 사용자만 /admin 접근 허용
                .anyRequest().permitAll()//다른 요청
        )
        .formLogin(formLogin -> formLogin
                .loginPage("/user/loginPage") // 커스텀 로그인 페이지 URL
                .loginProcessingUrl("/user/perform_login") // 로그인 form action URL
//                .defaultSuccessUrl("/") // 로그인 성공 시 리디렉션 URL successHandler로 대체
//                .failureUrl("/") // 로그인 실패 시 리디렉션 URL
                .usernameParameter("userId") // 로그인 form의 userId 파라미터 이름
                .passwordParameter("userPwd") // 로그인 form의 userPwd 파라미터 이름
                .successHandler(customSuccessHandler)
                .failureHandler(customFailureHandler)
                .permitAll()
        ).logout(logout -> logout
                .logoutUrl("/user/logout") // 로그아웃 처리 URL
                .logoutSuccessHandler(customLogoutSuccessHandler) // 로그아웃 성공 시 핸들러 설정
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 후 삭제할 쿠키 이름 설정
                .deleteCookies("rememberMe")
                .permitAll()
        ).rememberMe(remember -> remember
        		.rememberMeParameter("rememberMe")
        		.tokenValiditySeconds(60*60*24*7)
        		.alwaysRemember(true)
        		.userDetailsService(userDetailServiceImpl) // 사용자 계정 조회
//        		.authenticationSuccessHandler(customSuccessHandler)
        		
        		)
        ;
        



    	return http.build();
    }
    

    
}