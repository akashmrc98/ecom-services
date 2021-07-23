package com.ecom.sessions.jwts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	private Long userId;
	private String username;
	private String accessToken;
	private String refreshToken;
}
