package com.ecom.cart.client;


import com.ecom.cart.config.RestTemplateConfig;
import com.ecom.cart.domain.Product;
import com.ecom.cart.dto.CartProductsMapDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;


@Component
@AllArgsConstructor
public class ProductsClient {
    private static final Logger logger = LoggerFactory.getLogger(ProductsClient.class);

    private final RestTemplateConfig restTemplateConfig;

    public CartProductsMapDto getCartProducts(Iterable<Product> productIds) {
        try {
            return restTemplateConfig.getRestTemplate()
                    .postForObject( "http://localhost:8083/api/v1/products/ids", productIds, CartProductsMapDto.class);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
            return null;
        }
    }
}
