package com.korea.project.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.korea.project.dao.user.UserDAO;
import com.korea.project.dto.user.UserDetail;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	
	private final UserDAO userDAO;
	
	// 아이디 비번을 가지고 로그인 하기
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO user = userDAO.selectById(userId);
		
		if(user != null) {
			return new UserDetail(user);
		}
		
		return null;
	}
}
