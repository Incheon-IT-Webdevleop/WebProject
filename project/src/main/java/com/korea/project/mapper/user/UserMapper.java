package com.korea.project.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.vo.user.UserVO;

@Mapper
public interface UserMapper {
	
	// 아이디 비번을 가지고 로그인 하기
    UserVO selectById(String userId);
	
	// 회원가입
    void signUp(UserVO vo);
	
	// 세션에 유저 이름과 닉네임을 넣기 위한 조회
    SessionUserDTO selectNicknameById(String id);
}
