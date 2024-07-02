package com.korea.project.service.user;

import org.springframework.stereotype.Service;

import com.korea.project.vo.user.UserVO;

@Service
public interface UserService {

	
	// 회원가입
	public void register(UserVO vo);
}
