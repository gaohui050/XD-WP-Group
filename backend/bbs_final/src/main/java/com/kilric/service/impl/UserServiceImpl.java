package com.kilric.service.impl;

import com.kilric.enums.Role;
import com.kilric.mapper.UserMapper;
import com.kilric.po.UserPO;
import com.kilric.service.IUserService;
import com.kilric.utils.security.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
	public int login(String username, String password) {
		String ecdPwd = PasswordEncryption.encode (password);
		UserPO user = userMapper.findUserByUnAndPwd (username, ecdPwd);
		if (user != null){
			return 1;
		}else {
			return 0;
		}
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
	@Cacheable(value = "redisCache",key = "'redis_user_'+#username")
	public UserPO findUserByUsername(String username) {
		return userMapper.findUserByUsername (username);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
	public List<UserPO> findAllUsers() {
		return userMapper.findUsers ();
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int addUser(UserPO userPO) {
		return userMapper.addUser (userPO);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int deleteUserByUsername(String username) {
		return userMapper.deleteUserByUsername (username);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
	public int updateUser(UserPO userPO) {
		return userMapper.updateUser (userPO);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true,propagation = Propagation.REQUIRED)
	public Role getRoleByUsername(String username) {
		return userMapper.getRoleByUsername (username);
	}
}
