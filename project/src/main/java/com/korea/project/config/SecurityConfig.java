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
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 커스텀 로그인 페이지 URL
                        .loginProcessingUrl("/perform_login") // 로그인 form action URL
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 리디렉션 URL
                        .failureUrl("/login?error=true") // 로그인 실패 시 리디렉션 URL
                        .usernameParameter("username") // 로그인 form의 username 파라미터 이름
                        .passwordParameter("password") // 로그인 form의 password 파라미터 이름
                        .permitAll()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/user")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/admin")).hasRole("ADMIN")
                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login.userInfoEndpoint(userInfoEndpointConfig ->
//                                userInfoEndpointConfig.userService(customOAuth2UserService)))
        ;
        return http.build();
    }
}