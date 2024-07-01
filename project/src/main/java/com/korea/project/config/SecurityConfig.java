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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()) // 모든 요청을 permitAll()로 설정
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll())
            .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}