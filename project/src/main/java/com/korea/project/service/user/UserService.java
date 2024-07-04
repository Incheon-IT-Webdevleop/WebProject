package com.korea.project.service.user;

import org.springframework.stereotype.Service;

import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.SessionUserDTO;

@Service
public interface UserService {

	
	// 회원가입
	public void register(RegisterRequestDTO vo);
	
	// 세션에 유저 이름과 유저 닉네임을 저장하기 위한 조회
	public SessionUserDTO selectNicknameById(String id);
	
	// 아이디 중복체크
	public int checkDuplicateById(String id);
	
	// 이메일 중복체크
	public int checkDuplicateByEmail(String email);
	
	// 닉네임 중복체크
	public int checkDuplicateByNickname(String nickname);
}
