package com.kilric.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetUserRoleDTO {
	private String username;
	private String role;
	private String forum;
}
