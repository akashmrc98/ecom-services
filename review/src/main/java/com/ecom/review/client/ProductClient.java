package com.ecom.review.client;


import com.ecom.review.api.ProductConfig;
import com.ecom.review.config.RestTemplateConfig;
import com.ecom.review.dto.ReviewRatingsDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class ProductClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    private final RestTemplateConfig restTemplateConfig;
    private final ProductConfig productConfig;

    public void updateProductReviewsAndRatings(ReviewRatingsDto reviewRatingsDto, Long productId) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            String url = productConfig.getApi() + "reviews/" + productId.toString();
            logger.info(url);
            restTemplate.postForLocation(url, reviewRatingsDto);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
