package com.korea.project.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.korea.project.dao.user.UserDAO;
import com.korea.project.dto.user.FindRequestDTO;
import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.ResetPasswordRequestDTO;
import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDAO userDAO;
	
	// 세션에 등록하기 위한 아이디, 이름 조회
		@Override
		public SessionUserDTO selectNicknameById(String id) {
			return userDAO.selectNicknameById(id);
		}
		
		// 아이디 중복검사
		@Override
		public int checkDuplicateById(String id) {
			return userDAO.checkDuplicateById(id);
		}
		
		// 이메일 중복검사
		@Override
		public int checkDuplicateByEmail(String email) {
			// TODO Auto-generated method stub
			return userDAO.checkDuplicateByEmail(email);
		}
		
		// 닉네임 중복체크
		@Override
		public int checkDuplicateByNickname(String nickname) {
			// TODO Auto-generated method stub
			return userDAO.checkDuplicateByNickname(nickname);
		}
		
		// 회원가입
		@Override
		public void register(RegisterRequestDTO vo) {
//		   String rawPassword = vo.getUserPwd();
//	       String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//		   vo.setUserPwd(encPassword);
		   
		   userDAO.signUp(vo);
			
		}
		
		// 아이디, 비밀번호 찾기
		@Override
		public String find(FindRequestDTO dto) {
			return userDAO.find(dto);
		}
		
		// 아이디로 유저찾기
		@Override
		public UserVO selectById(String id) {
			return userDAO.selectById(id);
		}
		
		/**
		 * 비밀번호 변경
		 * @param ResetPasswordRequestDTO dto
		 */
		@Override
		public void resetPwd(ResetPasswordRequestDTO dto) {
			userDAO.updatePwd(dto);
			
		}
		
		/**
		 * 유저정보에 따른 비밀번호 일치 검사
		 * @param SessionUserDTO, inputPwd
		 * @return success, differError
		 */
		@Override
		public String checkPwd(String inputPwd, SessionUserDTO user) {
			String userPwd = userDAO.selectBySession(user).getUserPwd();
			String result = "";
			
			if(bCryptPasswordEncoder.matches(inputPwd, userPwd)) {
				result = "success";
			}else {
				result = "비밀번호가 일치하지 않습니다.";
			}
			
			return result;
		}
	
}
