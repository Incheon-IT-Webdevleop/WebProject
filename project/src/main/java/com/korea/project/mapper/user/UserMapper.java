package com.korea.project.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.dto.user.FindRequestDTO;
import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.ResetPasswordRequestDTO;
import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.vo.user.UserVO;

@Mapper
public interface UserMapper {
	
	// 아이디 비번을 가지고 로그인 하기
    UserVO selectById(String userId);
	
	// 회원가입
	public void signUp(RegisterRequestDTO vo);
	
	// 세션에 유저 이름과 닉네임을 넣기 위한 조회
	public SessionUserDTO selectNicknameById(String id);
	
	// 아이디 중복검사
	public int checkDuplicateById(String id);
	
	// 이메일 중복검사
	public int checkDuplicateByEmail(String email);
	
	// 닉네임 중복검사
	public int checkDuplicateByNickname(String nickname);
	
	// 찾기
	public String find(FindRequestDTO dto);
	
	/**
	 * 비밀번호 변경
	 * @param ResetPasswordRequestDTO dto
	 */
	public void updatePwd(ResetPasswordRequestDTO dto);

}
