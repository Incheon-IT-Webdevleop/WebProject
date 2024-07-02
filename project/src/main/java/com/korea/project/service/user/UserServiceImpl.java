package com.korea.project.service.user;

import org.springframework.stereotype.Service;

import com.korea.project.dao.user.UserDAO;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserDAO userDAO;
	
	@Override
	public void register(UserVO vo) {
		userDAO.signUp(vo);
	}
		
}
