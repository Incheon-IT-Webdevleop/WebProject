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
		return userVO.getUserPwd();  // UserVO에서 비밀번호를 반환
	}
	
	@Override
	public String getUsername() {
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
		System.out.println(collect);
		return collect;
	}
	  @Override
	    public boolean isAccountNonExpired() {
	        return true;  // 계정이 만료되지 않음을 나타냅니다
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;  // 계정이 잠기지 않았음을 나타냅니다
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;  // 자격 증명이 만료되지 않았음을 나타냅니다
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;  // 계정이 활성화되었음을 나타냅니다
	    }
}
