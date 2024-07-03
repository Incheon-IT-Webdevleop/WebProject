package com.korea.project.config;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDined extends AccessDeniedHandlerImpl{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 상태 코드 403 설정
        response.setContentType("text/plain;charset=UTF-8"); // 응답 컨텐츠 타입 설정
        response.getWriter().write("로그인이 필요한 서비스입니다."); // 메시지 출력
	}
}
