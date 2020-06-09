package com.kilric.mapper;

import com.kilric.enums.Role;
import com.kilric.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
	// 添加用户
	int addUser(UserPO user);
	
	int deleteUserByUsername(String username);
	
//	@Select ("select * from bbs.user")
	List<UserPO> findUsers();
	
	int updateUser(UserPO userPO);
	
	UserPO findUserByUsername(String username);
	
	Role getRoleByUsername(String username);
	
	UserPO findUserByUnAndPwd(String username,String ecdPwd);
}
