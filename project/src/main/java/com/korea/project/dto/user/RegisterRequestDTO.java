package com.korea.project.dto.user;

import lombok.Data;

@Data
public class RegisterRequestDTO {
	private String userId, userNickname, userPwd, userName, userEmail;
}
