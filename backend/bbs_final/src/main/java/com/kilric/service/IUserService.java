package com.kilric.service;

import com.kilric.enums.Role;
import com.kilric.po.UserPO;

import java.util.List;

public interface IUserService {
	
	int login(String username,String password);
	
	UserPO findUserByUsername(String username);
	
	List<UserPO> findAllUsers();
	
	int addUser(UserPO userPO);
	
	int deleteUserByUsername(String username);
	
	int updateUser(UserPO userPO);
	
	Role getRoleByUsername(String username);
}
