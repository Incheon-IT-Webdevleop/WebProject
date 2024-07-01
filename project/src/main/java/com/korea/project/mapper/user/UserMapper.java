package com.korea.project.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.dto.user.LoginRequestDTO;
import com.korea.project.vo.user.UserVO;

@Mapper
public interface UserMapper {
	
	// 아이디 비번을 가지고 로그인 하기
	public UserVO selectById(String userId);
	
	// 회원가입
	public int signUp(UserVO vo);
}
