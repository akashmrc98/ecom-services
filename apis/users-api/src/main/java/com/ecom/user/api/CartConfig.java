package com.ecom.user.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.cart")
@Data
public class CartConfig {
	private String host;
	private String api;

	public String getApi(){
		return host + api;
	}
}
