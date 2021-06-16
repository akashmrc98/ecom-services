package com.ecom.user.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.session")
@Data
public class SessionConfig {
	private String host;
	private String api;
	public String getApi(){
		return host + api;
	}
}
