package com.korea.project.dto.user.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.korea.project.vo.user.UserVO;

public class Oauth2UserDetails implements OAuth2User{
	
	private final UserVO userVO;
	private Map<String, Object> attributes;

    public Oauth2UserDetails(UserVO userVO, Map<String, Object> attributes) {

        this.userVO = userVO;
        this.attributes = attributes;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {

    	return userVO.getUserId();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				if(userVO.getUserRole() == 0) {
					return "ROLE_USER";
				}else {
					return "ROLE_ADMIN";
				}
				
			}
		});
//		System.out.println(collect);
		return collect;
	}

	    
}
