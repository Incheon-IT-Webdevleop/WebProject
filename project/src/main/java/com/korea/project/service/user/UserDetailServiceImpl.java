package com.korea.project.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.korea.project.dao.user.UserDAO;
import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.dto.user.UserDetail;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService, UserService{
	
	private final UserDAO userDAO;
	
	// 아이디 비번을 가지고 로그인 하기
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO user = userDAO.selectById(userId);
		if(user == null) {
			log.warn("User not found with userId: {}", userId);
			throw new UsernameNotFoundException("User not found with userId: " + userId);
		}
//		System.out.println("유저 디테일 서비스 임플");
		if(user.getUserRole() == 1) {
			user.setRoles("ADMIN");
		}else {
			user.setRoles("USER");
		}
		return new UserDetail(user);
	}
	
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
		userDAO.signUp(vo);
		
	}
}
