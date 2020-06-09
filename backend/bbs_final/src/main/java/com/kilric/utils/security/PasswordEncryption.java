package com.kilric.utils.security;

import org.springframework.util.Base64Utils;

/**
 * 密码加密解密
 */
public class PasswordEncryption {
	public static void main(String[] args) {
		String pwd = "12345678";
		String encode = PasswordEncryption.encode (pwd);
		System.out.println ("加密后："+encode);
		System.out.println ("解密后："+PasswordEncryption.decode (encode));
	}
	public static String encode(String OriginPwd){
		return Base64Utils.encodeToString (OriginPwd.getBytes ());
	}
	
	public static String decode(String encodePwd){
		byte[] bytes = Base64Utils.decodeFromString (encodePwd);
		return new String (bytes);
	}
}
