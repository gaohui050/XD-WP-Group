package com.kilric.config;

import com.kilric.interceptor.AdminInterceptor;
import com.kilric.interceptor.UserLoginInterceptor;
import com.kilric.interceptor.WebMasterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
	@Bean
	public AdminInterceptor loadAdminInterceptor(){
		return new AdminInterceptor ();
	}
	
	@Bean
	public WebMasterInterceptor loadWebMasterInterceptor(){
		return new WebMasterInterceptor ();
	}
	
	@Bean
	public UserLoginInterceptor  loadUserLoginInterceptor(){
		return new UserLoginInterceptor ();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor (loadUserLoginInterceptor ())
//				.addPathPatterns ("/essay/add","/comment/addComment","/user/profile");
//
//		registry.addInterceptor (loadWebMasterInterceptor ())
//				.addPathPatterns ("/essay/delete");
//
//		registry.addInterceptor (loadAdminInterceptor ())
//				.addPathPatterns ("/user/getUsers","/user/setRole","/user/deleteUser");
//
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping ("/**")
//				.allowCredentials (true)
//				.allowedHeaders ("*")
//				.allowedMethods ("POST", "GET", "PUT", "OPTIONS", "DELETE")
//				.allowedOrigins ("*")
//				.maxAge (3600000);
//	}
}
