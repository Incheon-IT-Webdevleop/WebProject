package com.korea.project.dao.user;

import org.springframework.stereotype.Repository;

import com.korea.project.dto.user.FindRequestDTO;
import com.korea.project.dto.user.FindResponseDTO;
import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.ResetPasswordRequestDTO;
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
	public void signUp(RegisterRequestDTO vo) {
		//System.out.println("회언가입 DAO");
		userMapper.signUp(vo);
	}
	
	// 세션에 유저 이름과 아이디를 넣기 위한 조회
	@Override
	public SessionUserDTO selectNicknameById(String id) {
		return userMapper.selectNicknameById(id);
	}
	
	// 아이디 중복검사
	@Override
	public int checkDuplicateById(String id) {
		return userMapper.checkDuplicateById(id);
	}
	
	// 이메일 중복검사
	@Override
	public int checkDuplicateByEmail(String email) {
		return userMapper.checkDuplicateByEmail(email);
	}
	
	// 닉네임 중복검사
	@Override
	public int checkDuplicateByNickname(String nickname) {
		return userMapper.checkDuplicateByNickname(nickname);
	}
	
	// 찾기
	@Override
	public String find(FindRequestDTO dto) {
		return userMapper.find(dto);
	}
	
	/**
	 * 비밀번호 변경
	 * @param ResetPasswordRequestDTO dto
	 */
	@Override
	public void updatePwd(ResetPasswordRequestDTO dto) {
		userMapper.updatePwd(dto);
	}
	
}
