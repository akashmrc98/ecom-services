package com.ecom.authentication.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
	private String accessSecretKey;
	private String accessTokenPrefix;
	private Integer accessTokenExpirationInHours;

	private String refreshSecretKey;
	private String refreshTokenPrefix;
	private Integer refreshTokenExpirationInDays;

	public SecretKey accessSecretKey(){
		return Keys.hmacShaKeyFor(getAccessSecretKey().getBytes(StandardCharsets.UTF_8));
	}

	public SecretKey refreshSecretKey(){
		return Keys.hmacShaKeyFor(getRefreshSecretKey().getBytes(StandardCharsets.UTF_8));
	}

}
