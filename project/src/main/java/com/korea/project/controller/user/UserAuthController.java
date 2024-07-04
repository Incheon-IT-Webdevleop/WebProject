package com.korea.project.controller.user;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.project.dto.user.RegisterRequestDTO;
import com.korea.project.service.user.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailServiceImpl userService;
	
	
	@GetMapping("/user/index")
	public String index() {
		return "index";
	}
	
	// 로그인 페이지에 점근하면 view 보여주기
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    

	
	// 회원가입 페이지에 접근하면 view보여주기
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}
	
	// 회원가입 페이지에서 회원가입 버튼을 눌렀을 때 작동하는 컨트롤러
    @PostMapping("/register")
    @ResponseBody
    public HashMap<String, String> postRegister(RegisterRequestDTO vo) {
        HashMap<String, String> map = new HashMap<>();
        vo.setUserEmail(vo.getLocalEmail()+vo.getDomainEmail());
        String reKey = "result";
        String meKey = "message";
        //System.out.println(vo.getUserPwd());
        
        // 유효성 검사
        if (vo.getUserId() == null || vo.getUserId().isEmpty()) {
            map.put(reKey, "empthIdError");
            map.put("message", "아이디를 입력해 주세요.");
            return map;
        }
        if(!isValid("id", vo.getUserId())) {
        	map.put(reKey, "idInvalidError");
        	map.put(meKey, "아이디는 한글 2자~6자 또는 영어 소문자 4자~12자입니다.");
        }
        if (vo.getUserEmail() == null || !isValid("email",vo.getUserEmail())) {
            map.put(reKey, "EmailInvalidError");
            map.put(meKey, "유효한 이메일을 입력해 주세요.");
            return map;
        }
        if (vo.getUserPwd() == null || vo.getUserPwd().isEmpty()) {
            map.put(reKey, "emtpyPwdError");
            map.put(meKey, "비밀번호를 입력해주세요.");
            return map;
        }
        if(!vo.getCheckPwd().equals(vo.getUserPwd())) {
        	map.put(reKey, "mismatchPwdError");
        	map.put(meKey, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        	return map;
        }
        if(!isValid("pwd", vo.getUserPwd()) || !isValid("pwd", vo.getCheckPwd())) {
        	map.put(reKey, "pwdInvalidError");
        	map.put(meKey, "비밀번호는 8자~ 20자의 영어 소문자와 숫자의 조합만 가능합니다.");
        	return map;
        }
        if (vo.getUserNickname() == null || vo.getUserNickname().isEmpty()) {
            map.put(reKey, "emptyNicknameError");
            map.put(meKey, "닉네임을 입력해 주세요.");
            return map;
        }
        if(!isValid("nickname", vo.getUserNickname())) {
        	map.put(reKey, "nicknameInvalidError");
        	map.put(meKey, "이름은 2자~6자의 한글만 가능합니다.");
        	return map;
        }

        // 비밀번호 암호화
        String rawPassword = vo.getUserPwd();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        vo.setUserPwd(encPassword);

        // 회원가입 처리
        //System.out.println("서비스 시작전");
        userService.register(vo);
        map.put("result", "success");
        //System.out.println("서비스 이후");
        return map;
    }
	
	/**
	 * @author mod459
	 * @param type(id, email, nickname)
	 * @param value(real input value)
	 * @return map<"isDuplicate", boolean>
	 */
	@PostMapping("/api/check-duplicate")
	@ResponseBody
	public HashMap<String, Boolean> checkDuplicate( String type, String value) {
		HashMap<String, Boolean> map = new HashMap<>();	
		String key = "isDuplicate";
		// true = 중복되었다
		// false = 중복되어있지 않다
		// 1보다 작다 = 0이다 = 중복되어 있지않다
		Boolean isDuplicate = true;
		if(type.equals("id")) {
			if(userService.checkDuplicateById(value) < 1 ) {
				// 중복되지 않음
				map.put(key, isDuplicate); 
			}else {
				// 중복됨
				map.put(key, !isDuplicate); 
			}
			return map;
		}else if(type.equals("email")) {
//			System.out.println(value);
			
			if(userService.checkDuplicateByEmail(value) < 1) {
				map.put(key, isDuplicate);
			}else {
				map.put(key, !isDuplicate);
			}
			return map;
		}else if(type.equals("nickname")) {
			if(userService.checkDuplicateByNickname(value) < 1) {
				map.put(key, isDuplicate);
			}else {
				map.put(key, !isDuplicate);
			}
			return map;
		}else {
			if(userService.checkDuplicateById(value) < 1) {
				map.put(key, isDuplicate);
			}else {
				map.put(key, !isDuplicate);
			}
			return map;
		}
	}
	
	// 유효성 검사
    private boolean isValid(String type, String value) {
        String regex = ""; 
        switch(type) {
        case "id":
        	regex = "^(?!.*admin)[a-z0-9]{5,20}$";
        	break;
        case "email":
        	regex =  "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        	break;
        case "nickname":
        	regex = "^(?:(?:[가-힣]{2,6})|(?:[a-z]{4,12}))(?!.*\\b(?:관리자|운영자|admin)\\b).*$";
        	break;
        case "pwd":
        	regex = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,20}$";
        	break;
        case "name":
        	regex = "^[가-힣]{2,6}$";
        	break;
        }
       
        return value.matches(regex);
    }
	


}	

