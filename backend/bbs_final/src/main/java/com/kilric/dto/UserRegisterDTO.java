package com.kilric.dto;

import com.kilric.enums.Role;
import com.kilric.po.UserPO;
import com.kilric.utils.security.PasswordEncryption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
	private String username;
	private String email;
	private String password;
	
	public static UserPO toUserPO(UserRegisterDTO userRegisterDTO){
		UserPO userPO = new UserPO ();
		userPO.setUsername (userRegisterDTO.getUsername ());
		userPO.setEmail (userRegisterDTO.getEmail ());
		String originPwd = userRegisterDTO.getPassword ();
		String ecdPwd = PasswordEncryption.encode (originPwd);
		userPO.setPassword (ecdPwd);
		userPO.setRole (Role.DEFAULT);
		return userPO;
	}
}
