package com.korea.project.controller.user;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.project.service.user.UserServiceImpl;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserServiceImpl userService;
	
	// 로그인 페이지에 점근하면 view 보여주기
	@GetMapping("/loginPage")
	public String loginPage() {

			return "user/login/login";
	}
	
	// 회원가입 페이지에 접근하면 view보여주기
	@GetMapping("register")
	public String registerPage() {
		System.out.println("register컨트롤러");
		return "user/register/register";
	}
	
	// 회원가입 페이지에서 회원가입 버튼을 눌렀을 때 작동하는 컨트롤러
	@PostMapping("/register")
	@ResponseBody
	public HashMap<String,String> postRegister(UserVO vo){
		String rawPassword = vo.getUserPwd();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		vo.setUserPwd(encPassword);
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("result", "success");
		


		return map;
	}
	
//	@PostMapping("/perform_login")
//	@ResponseBody
//	public HashMap<String, String> postLogin(LoginRequestDTO loginRequestDTO){
//		HashMap<String, String> map = new HashMap<>();
//		
//		map.put("parma","success");
//		return map;
//	}
	
}
