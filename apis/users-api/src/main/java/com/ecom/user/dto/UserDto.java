package com.ecom.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
	private Long userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String phone;
	private String email;
	private String username;
	private String password;
}
