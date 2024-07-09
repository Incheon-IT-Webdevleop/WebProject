package com.korea.project.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.service.user.UserDetailServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private final UserDetailServiceImpl userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
        /// HTTP 세션을 가져옵니다. (세션이 없으면 새로 생성)
        HttpSession session = request.getSession();
//        System.out.println("세션에 저장되어있는 정보 : " + session);
        // Authentication 객체에서 사용자 이름을 가져옵니다.
        String userId = authentication.getName();
        SessionUserDTO dto = userService.selectNicknameById(userId);
        
        // 사용자 이름을 세션에 저장합니다. 예시로 사용자 이름을 "username"이라는 이름으로 저장합니다.
        session.setAttribute("user",dto);
        session.setMaxInactiveInterval(60*30);
        
        if(userService.selectNicknameById(userId).getUserNickname() == null || userService.selectNicknameById(userId).getUserNickname().isEmpty()) {
        	response.sendRedirect("/user/set-nickname");
        	return;
        }	
        
        response.sendRedirect("/");

	}
	
}