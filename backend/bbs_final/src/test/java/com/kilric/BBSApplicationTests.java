package com.kilric;

import com.kilric.enums.Role;
import com.kilric.mapper.EssayMapper;
import com.kilric.mapper.UserMapper;
import com.kilric.po.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BBSApplicationTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EssayMapper essayMapper;
	
//	@Autowired
//	StuMapper stuMapper;
	@Test
	void testRemote(){
		UserPO admin = userMapper.findUserByUsername ("admin");
		System.out.println (admin);
	}
	
	@Test
	void contextLoads() {
//		UserPO jiaruigang = userMapper.findUserByUnAndPwd ("jiaruigang", "MTIzNDU2Nzg=");
//		System.out.println (jiaruigang);
//		System.out.println (userMapper.findUsers ());
//		System.out.println (essayMapper.findEssayByEssayId (1));
//		System.out.println (stuMapper.findStus ());
//		System.out.println (userMapper.getRoleByUsername ("jiaruigang").getName ());
//		System.out.println (userMapper.findUserByUsername ("jiaruigang"));
	}
	
	@Test
	void textUpdate(){
//		UserPO userPO = new UserPO ();
//		userPO.setRole (Role.fromName ("超级管理员"));
//		userPO.setPassword ("123456789");
//		userPO.setEmail ("653584667@qq.com");
//		userPO.setForum ("all");
//		int i = userMapper.updateUser (userPO);
//		if (i == 1){
//			System.out.println ("成功"+i);
//		}else {
//			System.out.println ("失败！"+i);
//		}
	}
	
	@Test
	void testRole(){
//		Role jiaruigang = userMapper.getRoleByUsername ("jiaruigang");
//		System.out.println (jiaruigang==Role.ADMIN);
	}
	
}
