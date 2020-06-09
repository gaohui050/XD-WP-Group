package com.kilric.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kilric.dto.*;
import com.kilric.enums.Role;
import com.kilric.po.UserPO;
import com.kilric.service.impl.MailServiceImpl;
import com.kilric.service.impl.UserServiceImpl;
import com.kilric.utils.security.PasswordEncryption;
import com.kilric.utils.security.TokenUtil;
import com.kilric.vo.UserLoginVO;
import com.kilric.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@RequestMapping(value = "/login",method = {RequestMethod.POST})
	public Map<String,Object> login(@RequestBody UserLoginDTO userLoginDTO){
		System.out.println (userLoginDTO);
		String username = userLoginDTO.getUsername ();
		String password = userLoginDTO.getPassword ();
		int res = userService.login (username, password);
		System.out.println (res);
		Map<String,Object> map = new HashMap<> ();
		if (res == 0){
			map.put ("data",null);
			map.put ("status", HttpStatus.BAD_REQUEST.value ());
		}else {
			UserLoginVO userLoginVO = new UserLoginVO ();
			String token = TokenUtil.getToken (username);
//			Role role = userService.getRoleByUsername (username);
			UserPO user = userService.findUserByUsername (username);
			userLoginVO.setToken (token);
			userLoginVO.setUsername (username);
			userLoginVO.setRole (user.getRole ().getName ());
			userLoginVO.setForum (user.getForum ());
			map.put ("data", userLoginVO);
			map.put ("status",HttpStatus.OK.value ());
		}
		return map;
	}
	
	@RequestMapping(value = "/profile/{username}",method = {RequestMethod.GET})
	public Map<String,Object> getProfile(@PathVariable String username){
		System.out.println (username);
		Map<String,Object> map = new HashMap<> ();
		UserPO userPO = userService.findUserByUsername (username);
		if (userPO == null){
			map.put ("data",null);
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
		}else {
			map.put ("data", UserVO.fromUserPO (userPO));
			map.put ("status",HttpStatus.OK.value ());
		}
		return map;
	}
	
	@RequestMapping(value = "/register",method = {RequestMethod.POST})
	public Map<String,Object> register(@RequestBody UserRegisterDTO userDTO){
		System.out.println (userDTO);
		Map<String,Object> map = new HashMap<> ();
		int i = userService.addUser (UserRegisterDTO.toUserPO (userDTO));
		if (i == 1){
			map.put ("status",HttpStatus.OK.value ());
		}else {
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
		}
		return map;
	}
	
	@RequestMapping(value = "/update",method = {RequestMethod.PUT})
	public Map<String,Object> updateProfile(@RequestBody UserUpdateDTO updateDTO){
		System.out.println (updateDTO);
		Map<String,Object> map = new HashMap<> ();
		UserPO userPO = userService.findUserByUsername (updateDTO.getUsername ());
		if (userPO == null){
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
			return map;
		}
//		if (updateDTO.getEmail () != null || Objects.equals (updateDTO.getEmail (),"")){
//
//		}
		userPO.setEmail (updateDTO.getEmail ());
		String originPwd = updateDTO.getPassword ();
		String ecdPwd = PasswordEncryption.encode (originPwd);
		userPO.setPassword (ecdPwd);
		if (updateDTO.getRole () != null || Objects.equals (updateDTO.getRole (),"")){
			userPO.setRole (Role.fromName (updateDTO.getRole ()));
		}
//		if (updateDTO.getForum () != null || ! Objects.equals (updateDTO.getForum (), "")){
//			userPO.setForum (updateDTO.getForum ());
//		}
		userPO.setForum (updateDTO.getForum ());
		int i = userService.updateUser (userPO);
		if (i == 1){
			map.put ("status",HttpStatus.OK.value ());
		}else {
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
		}
		return map;
	}
	
	@RequestMapping(value = "/findPassword",method = {RequestMethod.POST})
	public Map<String,Object> findPassword(@RequestBody FindPasswordDTO passwordDTO){
		UserPO user = userService.findUserByUsername (passwordDTO.getUsername ());
		Map<String,Object> map = new HashMap<> ();
		
		if (user == null){
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
			map.put ("message","用户名不正确");
			return map;
		}
		if(! user.getEmail ().equals (passwordDTO.getEmail ())){
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
			map.put ("message","邮箱错误");
			return map;
		}
		String ecdPwd = user.getPassword ();
		String dcdPwd = PasswordEncryption.decode (ecdPwd);
		String content = "亲爱的"+passwordDTO.getUsername () + ":\n"+
								 "\t您好！\n" +
				                 "\n这是您的密码，请牢记您的密码：" + dcdPwd +"\n\n\n" +
								 "祝您生活愉快！\n\n"+
								 "From:西电技术交流论坛\n"+
				                 LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss"));
		mailService.sendPassword (passwordDTO.getEmail (),"西电技术交流论坛-密码找回",content);
		map.put ("status",HttpStatus.OK.value ());
		map.put ("message","处理成功");
		return map;
	}
	
	@RequestMapping(value = "/getUsers",method = {RequestMethod.POST})
	public Object getUserList(@RequestBody OrdinaryPageEntityDTO ordinaryPageEntityDTO){
		PageHelper.startPage (ordinaryPageEntityDTO.getPageNum (), ordinaryPageEntityDTO.getPageSize ());
		List<UserPO> allUsers = userService.findAllUsers ();
		List<UserVO> users = new ArrayList<> ();
		for (UserPO user : allUsers) {
			users.add (UserVO.fromUserPO (user));
		}
		PageInfo<UserVO> pageInfo = new PageInfo<> (users);
		Map<String,Object> map = new HashMap<> ();
		map.put ("status", HttpStatus.OK.value ());
		map.put ("data",pageInfo);
		return map;
	}
	
	@RequestMapping(value = "/setRole",method = {RequestMethod.POST})
	public Map<String,Object> setUserRole(HttpServletRequest request, @RequestBody SetUserRoleDTO userRoleDTO){
		String token = request.getHeader ("token");
		String username = TokenUtil.verifyToken (token);
		Role role = userService.getRoleByUsername (username);
		Map<String,Object> map = new HashMap<> ();
		if (role != Role.ADMIN){
			map.put ("status",HttpStatus.FORBIDDEN.value ());
			map.put ("message","没有权限");
			return map;
		}
		UserPO userPO = new UserPO ();
		userPO.setUsername (userRoleDTO.getUsername ());
		userPO.setRole (Role.fromName (userRoleDTO.getRole ()));
		userPO.setForum (userRoleDTO.getForum ());
		int i = userService.updateUser (userPO);
		if (i != 1){
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
			map.put ("message","设置失败");
		}else {
			map.put ("status",HttpStatus.OK.value ());
			map.put ("message","设置成功");
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteUser/{username}",method = {RequestMethod.POST})
	public Map<String,Object> deleteUser(@PathVariable String username){
		System.out.println (username);
//		String token = request.getHeader ("token");
//		String unameAdmin = TokenUtil.verifyToken (token);
//		Role role = userService.getRoleByUsername (unameAdmin);
		Map<String,Object> map = new HashMap<> ();
////		if (role != Role.ADMIN){
////			map.put ("status",HttpStatus.FORBIDDEN.value ());
////			map.put ("message","没有权限");
////			return map;
////		}
		int i = userService.deleteUserByUsername (username);
		if (i != 1){
			map.put ("status",HttpStatus.BAD_REQUEST.value ());
			map.put ("message","删除失败");
		}else {
			map.put ("status",HttpStatus.OK.value ());
			map.put ("message","删除成功");
		}
		return map;
	}
}
