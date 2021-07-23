package com.ecom.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	private final RestTemplate restTemplate = new RestTemplate();

	@Bean
	public RestTemplate getRestTemplate(){
		return restTemplate;
	}
}
