package com.ecom.user.client;


import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.api.WishListConfig;
import com.ecom.user.dto.request.RegisterDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class WishlistClient {

    private static final Logger logger = LoggerFactory.getLogger(WishlistClient.class);

    private final WishListConfig wishListConfig;
    private final RestTemplateConfig restTemplateConfig;

    public void createWishListServiceForUser(RegisterDto registerDto) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            logger.info(wishListConfig.getApi());
            restTemplate.postForLocation(wishListConfig.getApi(), registerDto);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
