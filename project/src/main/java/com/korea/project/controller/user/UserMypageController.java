package com.korea.project.controller.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.korea.project.service.user.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserMypageController {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailServiceImpl userService;
	

}
