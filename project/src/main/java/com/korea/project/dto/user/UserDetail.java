package com.korea.project.dto.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.korea.project.vo.user.UserVO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserDetail implements UserDetails{
	
	private final UserVO userVO;
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
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
		return collect;
	}
}
