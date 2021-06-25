package com.ecom.products.client;


import com.ecom.products.api.ReviewConfig;
import com.ecom.products.config.RestTemplateConfig;
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

    public void createReviewServiceForUser() {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            logger.info(reviewConfig.getApi());
//            restTemplate.postForObject(reviewConfig.getApi() + "/products");
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
