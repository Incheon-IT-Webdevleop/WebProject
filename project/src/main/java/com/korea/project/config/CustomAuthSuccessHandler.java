package com.korea.project.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.korea.project.dto.user.SessionUserDTO;
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
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	private final UserDetailServiceImpl userService;
	private final HttpSession session;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 Object principal = authentication.getPrincipal();
		 SessionUserDTO dto = null;
		 HttpSession session = request.getSession();
		 
		 if (principal instanceof OAuth2User) {
			 OAuth2User oAuth2User = (OAuth2User) principal;

	            String provider = oAuth2User.getAttribute("provider");
	            String providerId = oAuth2User.getAttribute("providerId");
	            String email = oAuth2User.getAttribute("email");
	            String name = oAuth2User.getAttribute("name");
	            
	           	// 저장된 
	          //  dto = userService.

	            log.info("userVO: {}", dto);

	            session.setAttribute("user", dto);
		 }else if(principal instanceof UserVO) {
			/// HTTP 세션을 가져옵니다. (세션이 없으면 새로 생성)
		        
//		        System.out.println("세션에 저장되어있는 정보 : " + session);
		        // Authentication 객체에서 사용자 이름을 가져옵니다.
		        String userId = authentication.getName();
		        dto = userService.selectNicknameById(userId);
		        
		        // 사용자 이름을 세션에 저장합니다. 예시로 사용자 이름을 "username"이라는 이름으로 저장합니다.
		       
//		        System.out.println("세션 정보 : " + session.getAttribute("user"));
		 } 
		 session.setAttribute("user",dto);
         session.setMaxInactiveInterval(60*30);
		
		
        super.onAuthenticationSuccess(request, response, authentication);
	}
	
}