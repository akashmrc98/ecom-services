package com.example.orders.client;


import com.example.orders.api.ProductConfig;
import com.example.orders.config.RestTemplateConfig;
import com.example.orders.dto.UpdateStockDto;
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

    public void updateProductReviewsAndRatings(UpdateStockDto updateStockDto) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            String url = productConfig.getApi() + "update-stock";
            logger.info(url);
            restTemplate.postForLocation(url, updateStockDto);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
