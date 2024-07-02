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
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.korea.project.service.user.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
//	private UserDe
	private final UserDetailServiceImpl userDetailServiceImpl;

	
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
        		.requestMatchers("/user").authenticated()
                // /user : 인증만 되면 들어갈 수 있는 주소
                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                //MANAGER, ADMIN 을 가진 사용자만 /manager 접근 허용
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                //ADMIN을 가진 사용자만 /admin 접근 허용
                .anyRequest().permitAll()//다른 요청
        )
        .formLogin(formLogin -> formLogin
                .loginPage("/user/loginPage") // 커스텀 로그인 페이지 URL
                .loginProcessingUrl("/user/perform_login") // 로그인 form action URL
                .defaultSuccessUrl("/") // 로그인 성공 시 리디렉션 URL successHandler로 대체
                .failureUrl("/") // 로그인 실패 시 리디렉션 URL
                .usernameParameter("userId") // 로그인 form의 userId 파라미터 이름
                .passwordParameter("userPwd") // 로그인 form의 userPwd 파라미터 이름
                .successHandler((req, res, authentication) ->{
                	System.out.println("authentication : " + authentication.getName());
                    res.sendRedirect("/");
                })
                .failureHandler((req, res, exception) -> {
                    System.out.println("exception : " + exception.getMessage());
                    res.sendRedirect("/user/register");
                })
                .permitAll()
        )
        
        .httpBasic(AbstractHttpConfigurer::disable);


    	return http.build();
    }
    
    
}