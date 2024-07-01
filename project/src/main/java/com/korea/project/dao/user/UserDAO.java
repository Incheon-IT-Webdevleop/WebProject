package com.korea.project.dao.user;

import org.springframework.stereotype.Repository;

import com.korea.project.dto.user.LoginRequestDTO;
import com.korea.project.mapper.user.UserMapper;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDAO implements UserMapper{
	private final UserMapper userMapper;
	
	// 아이디 비번을 가지고 로그인 하기
	@Override
	public UserVO selectById(String userId) {
		
		return userMapper.selectById(userId);
	}
	
	// 회원가입
	@Override
	public int signUp(UserVO vo) {
		return userMapper.signUp(vo);
	}
	
}
