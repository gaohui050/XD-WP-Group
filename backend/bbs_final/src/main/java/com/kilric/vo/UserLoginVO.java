package com.kilric.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO implements Serializable {
	private String token;
	private String username;
	private String role;
	private String forum;
}
