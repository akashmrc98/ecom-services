package com.ecom.authentication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SessionDto {
	private Long userId;
	private String username;
	private String password;
	private String userRole;
}
