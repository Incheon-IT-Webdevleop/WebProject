package com.korea.project.service.user;

import org.springframework.stereotype.Service;

import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.vo.user.UserVO;

@Service
public interface UserService {

	
	// 회원가입
	public void register(UserVO vo);
	
	// 세션에 유저 이름과 유저 닉네임을 저장하기 위한 조회
	public SessionUserDTO selectNicknameById(String id);
}
