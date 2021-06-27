package com.ecom.cart.client;

import com.ecom.cart.api.ProductConfig;
import com.ecom.cart.dto.ProductsMapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsClient {

    private final RestTemplate restTemplate;
    private final ProductConfig productConfig;

    public ProductsMapDto getProducts(List<Long> products){
        return restTemplate.postForObject(productConfig.getApi() + "/ids", products, ProductsMapDto.class);
    }

}
