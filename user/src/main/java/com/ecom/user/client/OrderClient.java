package com.ecom.user.client;

import com.ecom.user.api.OrderConfig;
import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.dto.request.RegisterDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class OrderClient {
    private static final Logger logger = LoggerFactory.getLogger(OrderClient.class);

    private final OrderConfig orderConfig;
    private final RestTemplateConfig restTemplateConfig;

    public void createOrderServiceForUser(RegisterDto registerDto) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            logger.info(orderConfig.getApi());
            restTemplate.postForLocation(orderConfig.getApi(), registerDto);
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause().getMessage());
        }
    }
}
