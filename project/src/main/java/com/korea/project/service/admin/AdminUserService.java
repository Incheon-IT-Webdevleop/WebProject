package com.korea.project.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.project.mapper.admin.AdminUserMapper;
import com.korea.project.vo.user.UserVO;

@Service
public class AdminUserService {
	private final AdminUserMapper adminUserMapper;
	
    public AdminUserService(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public List<UserVO> getAllUsers() {
        return adminUserMapper.selectUser();
    }
    public boolean deleteUser(int userIdx) {
        int rowsAffected = adminUserMapper.updateUser(userIdx);
        return rowsAffected > 0;
    }
}
