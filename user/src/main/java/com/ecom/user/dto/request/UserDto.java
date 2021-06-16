package com.ecom.user.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private Long userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String phone;
	private String email;
	private String username;
	private String password;
}
