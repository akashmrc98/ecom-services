package com.ecom.wishlist.client;

import com.ecom.wishlist.config.RestTemplateConfig;
import com.ecom.wishlist.domain.Product;
import com.ecom.wishlist.dto.WishListProductsMapDto;
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

    public WishListProductsMapDto getCartProducts(Iterable<Product> productIds) {
        try {
            return restTemplateConfig.getRestTemplate()
                    .postForObject( "http://localhost:8083/api/v1/products/ids", productIds, WishListProductsMapDto.class);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
            return null;
        }
    }
}
