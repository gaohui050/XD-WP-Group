package com.kilric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.kilric.mapper"})
//@EnableCaching
public class BBSApplication {
	
	public static void main(String[] args) {
		SpringApplication.run (BBSApplication.class, args);
	}
	
}
