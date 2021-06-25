package com.ecom.user.client;

import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.api.ReviewConfig;
import com.ecom.user.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ReviewClient {
    private static final Logger logger = LoggerFactory.getLogger(ReviewClient.class);

    private final RestTemplateConfig restTemplateConfig;
    private final ReviewConfig reviewConfig;

    public void createReviewServiceForUser(RegisterDto registerDto) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            logger.info(reviewConfig.getApi());
            restTemplate.postForLocation(reviewConfig.getApi(), registerDto);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
