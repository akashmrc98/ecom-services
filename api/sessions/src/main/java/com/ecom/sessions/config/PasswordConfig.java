package com.ecom.sessions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class PasswordConfig {
	public String encode(String password)  {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public boolean match(String requestedPassword, String resourcePassword){
		return BCrypt.checkpw(requestedPassword, resourcePassword);
	}
}
