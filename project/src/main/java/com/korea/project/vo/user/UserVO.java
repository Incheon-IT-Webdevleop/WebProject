package com.korea.project.vo.user;

import lombok.Data;

@Data
public class UserVO {
	private String userId, userNickname, userPwd, userName, userEmail, regdate;
	private int userIdx, userRole, userDel;
}
