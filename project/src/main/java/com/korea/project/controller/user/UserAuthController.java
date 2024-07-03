package com.korea.project.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.korea.project.service.user.UserDetailServiceImpl;
import com.korea.project.vo.user.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailServiceImpl userService;
	private final AuthenticationManager authenticationManager;
	
	@GetMapping("/user/index")
	public String index() {
		return "index";
	}
	
	// 로그인 페이지에 점근하면 view 보여주기
    @GetMapping("/login")
    public String login() {
        return "user/login/login";
    }
    
//    @PostMapping("/perform_login")
//    @ResponseBody
//    public Map<String, Object> performLogin(@RequestParam("userId") String userId,
//                                     @RequestParam("userPwd") String userPwd) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        Map<String, Object> response = new HashMap<>();
//        // 사용자 정의 유효성 검사
//        if (userId.isEmpty() || userPwd.isEmpty()) {
//            response.put("status", "success");
//            response.put("redirectUrl", "/user/index");
//            return response;
//        }
//        System.out.println("간다");
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userId, userPwd)
//            );
//            System.out.println(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            response.put("status", "success");
//            modelAndView.setViewName("redirect:/user/index");
//        } catch (BadCredentialsException bad) {
//            response.put("status", "error");
//            response.put("message", "아이디 또는 비밀번호를 확인해주세요.");
//        }
//
//        return response;
//    }
	
	// 회원가입 페이지에 접근하면 view보여주기
	@GetMapping("/register")
	public String registerPage() {
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
	
	@PostMapping("/api/check-duplicate")
	@ResponseBody
	public HashMap<String, String> checkDuplicate( String type, String value) {
		HashMap<String, String> map = new HashMap<>();	
		if(type.equals("id")) {
			
			return map;
		}else if(type.equals("email")) {
			return map;
		}else if(type.equals("nickname")) {
			return map;
		}else {
			return map;
		}
	}

	

}	

