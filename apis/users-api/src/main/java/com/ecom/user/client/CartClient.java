package com.ecom.user.client;

import com.ecom.user.api.CartConfig;
import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CartClient {
	private static final Logger logger = LoggerFactory.getLogger(CartClient.class);

	private final RestTemplateConfig restTemplateConfig;
	private final CartConfig cartConfig;

	public void createCartServiceForUser(RegisterDto registerDto) {
		try {
			RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
			logger.info(cartConfig.getApi());
			restTemplate.postForLocation(cartConfig.getApi(), registerDto);
		} catch (RestClientException e) {
			logger.error(e.getMessage());
			logger.error(e.getCause().getMessage());
		}
	}
}
