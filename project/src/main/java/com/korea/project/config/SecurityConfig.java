package com.korea.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
//        this.customOAuth2UserService = customOAuth2UserService;
//    }
 
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    	http
    	.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(new MvcRequestMatcher(introspector, "/login")).permitAll() // 로그인 페이지 접근 허용
                .anyRequest().permitAll() // 나머지 모든 요청은 인증 필요
        )
        .formLogin(formLogin -> formLogin
                .loginPage("/login") // 커스텀 로그인 페이지 URL
                .loginProcessingUrl("/perform_login") // 로그인 form action URL
                .defaultSuccessUrl("/") // 로그인 성공 시 리디렉션 URL
                .failureUrl("/") // 로그인 실패 시 리디렉션 URL
                .usernameParameter("userId") // 로그인 form의 userId 파라미터 이름
                .passwordParameter("userPwd") // 로그인 form의 userPwd 파라미터 이름
                .permitAll()
        )
        .httpBasic(AbstractHttpConfigurer::disable);

    	return http.build();
    }
}