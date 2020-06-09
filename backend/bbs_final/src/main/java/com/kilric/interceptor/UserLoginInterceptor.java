package com.kilric.interceptor;

import com.kilric.utils.security.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 是否登录拦截
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader ("token");
		String username = null;
		if (token != null){
			username = TokenUtil.verifyToken (token);
		}
		return username != null;
	}
}
