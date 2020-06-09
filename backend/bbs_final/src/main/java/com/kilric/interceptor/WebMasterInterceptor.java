package com.kilric.interceptor;


import com.kilric.enums.Role;
import com.kilric.mapper.UserMapper;
import com.kilric.utils.security.TokenUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 是否为版主拦截
 */
@Component
public class WebMasterInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader ("token");
		String username = TokenUtil.verifyToken (token);
		if (username == null || username.equals ("")){
			return false;
		}else {
			Role role = userMapper.getRoleByUsername (username);
			System.out.println (role == Role.WEBMASTER || role == Role.ADMIN);
			return role == Role.WEBMASTER || role == Role.ADMIN;
		}
	}
	
}
