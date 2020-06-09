package com.kilric.po;

import com.kilric.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPO implements Serializable {
	private Integer userId;
	private String username;
	private String email;
	private String password;
	private Role role;
	private String forum;
}
