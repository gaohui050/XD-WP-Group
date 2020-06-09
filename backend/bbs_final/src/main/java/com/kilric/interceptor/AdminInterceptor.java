package com.kilric.interceptor;

import com.kilric.enums.Role;
import com.kilric.mapper.UserMapper;
import com.kilric.utils.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	
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
			return role == Role.ADMIN;
		}
	}
}
