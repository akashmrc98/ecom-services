package com.ecom.review.client;

import com.ecom.review.api.OrderConfig;
import com.ecom.review.config.RestTemplateConfig;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class OrderClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    private final RestTemplateConfig restTemplateConfig;
    private final OrderConfig orderConfig;

    public void updateIsReviewedProduct(Long orderId, int index) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            String url = orderConfig.getApi() + orderId.toString() + "/products/" + index;
            logger.info(url);
            restTemplate.postForLocation(url, null);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
