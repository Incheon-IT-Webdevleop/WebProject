package com.korea.project.service.user;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

	 @Override
	    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	        OAuth2User user = super.loadUser(userRequest);
	        
	        // Naver에서 받은 사용자 정보 처리
	        if ("naver".equals(userRequest.getClientRegistration().getRegistrationId())) {
	            return processNaverUser(user);
	        }
	        
	        return user;
	    }
	
	    private OAuth2User processNaverUser(OAuth2User user) {
	        // Naver 사용자 정보 처리 로직
	        // 예: 사용자 정보를 데이터베이스에 저장하거나 추가 정보 설정
	        return user;
	    }
}
