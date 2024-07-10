package com.korea.project.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.korea.project.vo.user.UserVO;
@Mapper
public interface AdminUserMapper {
	List<UserVO> selectUser();
    int updateUser(int userIdx);
}
