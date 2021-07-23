package com.example.orders.client;


import com.example.orders.api.ProductConfig;
import com.example.orders.config.RestTemplateConfig;
import com.example.orders.domain.Product;
import com.example.orders.dto.OrderProductsMapDto;
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

    public OrderProductsMapDto getOrderProducts(Iterable<Product> productIds) {
        try {
            return restTemplateConfig.getRestTemplate()
                    .postForObject( "http://localhost:8083/api/v1/products/ids", productIds, OrderProductsMapDto.class);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
            return null;
        }
    }
}
