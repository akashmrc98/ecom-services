package com.ecom.authentication.jwt;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse {
	private Long userId;
	private String username;
	private String accessToken;
	private String refreshToken;
}
