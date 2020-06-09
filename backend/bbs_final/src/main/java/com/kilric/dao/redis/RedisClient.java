package com.kilric.dao.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {
	// token 过期时间
	public static final long TOKEN_EXPIRE_TIME = 1800;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public void set(String key,String value){
		redisTemplate.opsForValue ().set (key,value);
	}
	
	public void set(String key,String value,long time){
		redisTemplate.opsForValue ().set (key, value);
		if (time > 0){
			redisTemplate.expire (key,time, TimeUnit.SECONDS);
		}else {
			redisTemplate.expire (key,TOKEN_EXPIRE_TIME,TimeUnit.SECONDS);
		}
	}
	
	public String get(String key){
		return redisTemplate.opsForValue ().get (key);
	}
	
	public boolean remove(String key){
		assert redisTemplate != null;
		return redisTemplate.delete (key);
	}
}
