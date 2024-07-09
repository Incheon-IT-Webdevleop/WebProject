package com.korea.project.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.service.admin.AdminService;
import com.korea.project.service.user.UserDetailServiceImpl;
import com.korea.project.service.user.UserServiceImpl;
import com.korea.project.vo.user.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private final AdminService adminService;	
	private final HttpSession session;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 HttpSession session = request.getSession();
		 
		 
		   getRedirectStrategy().sendRedirect(request, response, "/admin/home");

	}
	
}