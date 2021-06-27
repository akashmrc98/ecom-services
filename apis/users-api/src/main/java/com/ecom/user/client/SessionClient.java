package com.ecom.user.client;

import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.api.SessionConfig;
import com.ecom.user.dto.SessionDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class SessionClient {

	private static final Logger logger = LoggerFactory.getLogger(SessionClient.class);

	private final SessionConfig sessionConfig;
	private final RestTemplateConfig restTemplateConfig;

	public void createSessionServiceForUser(SessionDto sessionDto) {
		try {
			RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
			logger.info(sessionConfig.getApi());
			restTemplate.postForLocation(sessionConfig.getApi(), sessionDto);
		} catch (RestClientException e) {
			logger.error(e.getMessage());
			logger.error(e.getCause().getMessage());
		}
	}

}
