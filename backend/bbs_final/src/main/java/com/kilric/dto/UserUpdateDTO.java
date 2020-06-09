package com.kilric.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
	private String username;
	private String email;
	private String password;
	private String role;
	private String forum;
}
