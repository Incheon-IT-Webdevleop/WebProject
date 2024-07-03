package com.korea.project.controller.user;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
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
    public ModelAndView login() {
        return new ModelAndView("user/login/login");
    }
    
    @PostMapping("/perform_login")
    public ModelAndView performLogin(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();

        // 사용자 정의 유효성 검사
        if (username.isEmpty() || password.isEmpty()) {
            modelAndView.addObject("error", "아이디와 비밀번호를 입력해주세요.");
            modelAndView.setViewName("login");
            return modelAndView;
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            modelAndView.setViewName("redirect:/user/index");
        } catch (AuthenticationException e) {
            modelAndView.addObject("error", "로그인에 실패했습니다.");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }
	
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

