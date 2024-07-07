package com.korea.project.service.user;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.project.dao.board.BoardDAO;
import com.korea.project.dao.user.UserDAO;
import com.korea.project.dto.board.BoardListRequest;
import com.korea.project.dto.board.Pagination;
import com.korea.project.dto.board.PagingResponse;
import com.korea.project.dto.user.FindRequestDTO;
import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.dto.user.ResetPasswordRequestDTO;
import com.korea.project.dto.user.SessionUserDTO;
import com.korea.project.vo.board.BoardVO;
import com.korea.project.vo.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDAO userDAO;
	private final BoardDAO boardDAO;
	private final HttpSession session;
	
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
	
		/**
		 * 세션 정보에 따른 유저 조회
		 * @param SessionUserDTO user
		 * @return userVO
		 */
		@Override
		public UserVO selectBySession(SessionUserDTO user) {
			return userDAO.selectBySession(user);
		}
		
		/**
		 * 비밀번호 변경 서비스에서 하는것으로 새로 작성
		 * @param SessionUserDTO, ResetPasswordRequestDTO
		 * @return String message
		 */
		
		@Override
		@ResponseBody
		public String updatePwd(SessionUserDTO user, ResetPasswordRequestDTO dto) {
			
			// 세션에 정보가 없으면
			if(user == null) {
				return "Wrong Approach";
			}
			// 입력값이 없으면
			if(dto == null) {
				return "Wrong Request";
			}
			
			// 입력한 비밀버호 두개가 같지 않을 때
			if(!dto.getNewPassword().equals(dto.getConfirmPassword())) {
				return "Diff Pwd";
			}
			
			// 세션 정보로 유저 정보 찾기
			UserVO vo = userDAO.selectBySession(user);
			
			if(bCryptPasswordEncoder.matches(dto.getConfirmPassword(), vo.getUserPwd())){
				return "Same Pwd";
			}
			
			if(!bCryptPasswordEncoder.matches(dto.getCurrentPassword(),userDAO.selectBySession(user).getUserPwd())) {
	    		return "Wrong Pwd";
	    	}
			
			// 암호화
			dto.setEncodingPwd(bCryptPasswordEncoder.encode(dto.getConfirmPassword()));
			dto.setUserId(vo.getUserId());
			
			userDAO.updatePwd(dto);
			
			return "success";
		}
		/**
		 * 탈퇴
		 * @param SessionUserDTo
		 * @return String result
		 */
		@Override
		public String withdraw(SessionUserDTO user) {
			
			userDAO.updateUserDel(user);
			session.removeAttribute("user");
			return "success";
		}
		
		
		/**
		 * 마이페이지 게시글 목록
		 * @params BoardListRequest, SessionUserDTO
		 * @return PaginResponse<BoardVO>
		 */
		@Override
		public PagingResponse<BoardVO> myPost(BoardListRequest params, SessionUserDTO user) {
	  		int nowPage = 1;
	  		System.out.println("con nowPage : " + params.getNowpage());
	  		if(params.getNowpage() != 0) {
	  			nowPage = params.getNowpage();
	  		}
	  	
	  		params.setNowpage(nowPage);
	  		
	  		int count = boardDAO.count(params);
			if(count < 1) {
				return new PagingResponse<>(Collections.emptyList(), null);
			}
			
			//Pagination 객체를 생성해서 페이지 정보 계산 후 params에 계산된 페이지 정보 
			Pagination pagination = new Pagination(count, params);
			params.setPagination(pagination);

			//계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 반환
			List<BoardVO> list = boardDAO.findBoardList(params);
			return  new PagingResponse<>(list, pagination);
		}
		
		
}
