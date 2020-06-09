package com.kilric.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
	public static void main(String[] args) {
		String token = TokenUtil.getToken ("jiaruigang");
		System.out.println ("token:"+token);
		String username = TokenUtil.verifyToken (token);
		System.out.println (username);
	}
	// 密钥
	private static final String SECRET = "JIARUIGANG-PLMOKNIJBUHVYGCTFXRDZESWAQ->>?@#$%^&*(<<";
	// 通过用户名获取token
	public static String getToken(String username){
		// 签发时间
		Date issuedAtDate = new Date ();
//		Calendar nowTime = Calendar.getInstance ();
//		// 30分钟过期
//		nowTime.add (Calendar.MINUTE, 60);
//		Date expireDate = nowTime.getTime ();
		
		Map<String,Object> header = new HashMap<> ();
		header.put ("alg","HS256");
		header.put ("typ","JWT");
		
		return JWT.create ()
				       .withHeader (header)
				       .withClaim ("username", username)
				       .withIssuedAt (issuedAtDate)
				       .sign (Algorithm.HMAC256 (SECRET));
	}
	
	// 通过token获取用户名
	public static String verifyToken(String token){
		JWTVerifier jwtVerifier = JWT.require (Algorithm.HMAC256 (SECRET)).build ();
		DecodedJWT decodedJWT = null;
		String username = null;
		try {
			if (token != null){
				decodedJWT = jwtVerifier.verify (token);
				username = decodedJWT.getClaim ("username").asString ();
			}
		} catch (JWTVerificationException e) {
			e.printStackTrace ();
		}
		return username;
	}
	
	
}
