package com.korea.project.dao.user;

import org.springframework.stereotype.Repository;

import com.korea.project.dto.user.SessionUserDTO;
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
	public void signUp(UserVO vo) {
		userMapper.signUp(vo);
	}
	
	// 세션에 유저 이름과 아이디를 넣기 위한 조회
	@Override
	public SessionUserDTO selectNicknameById(String id) {
		return userMapper.selectNicknameById(id);
	}
	
}
