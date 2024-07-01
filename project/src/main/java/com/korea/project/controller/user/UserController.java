package com.korea.project.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/register")
	public String registerPage() {
		return "all/register";
	}
}
