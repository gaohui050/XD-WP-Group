package com.kilric.enums;

/**
 * 用户角色
 */
public enum Role {
	DEFAULT(0,"普通用户"),WEBMASTER(1,"版主"),ADMIN(2,"超级管理员");

	private int code;
	private String name;

	Role(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

	public void setCode(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}

	public static Role fromCode(int code){
		if (code == 2)
			return Role.ADMIN;
		if (code == 1)
			return Role.WEBMASTER;
		else
			return Role.DEFAULT;
	}

	public static Role fromName(String name){
		if (name.equals ("超级管理员"))
			return Role.ADMIN;
		if (name.equals ("版主"))
			return Role.WEBMASTER;
		else
			return Role.DEFAULT;
	}
}
