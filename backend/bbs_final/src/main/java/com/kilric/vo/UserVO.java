package com.kilric.vo;

import com.kilric.po.UserPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private String username;
	private String email;
	private String role;
	private String forum;
	
	public static UserVO fromUserPO(UserPO userPO){
		UserVO userVO = new UserVO ();
		userVO.setUsername (userPO.getUsername ());
		userVO.setEmail (userPO.getEmail ());
		userVO.setRole (userPO.getRole ().getName ());
		userVO.setForum (userPO.getForum ());
		return userVO;
	}
}
