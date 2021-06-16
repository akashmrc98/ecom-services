package com.ecom.gateway.client;

import com.ecom.gateway.api.SessionConfig;
import com.ecom.gateway.config.RestTemplateConfig;
import com.ecom.gateway.dto.AuthDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class SessionClient {
    private final RestTemplateConfig restTemplateConfig;
    private final SessionConfig sessionConfig;

    public Boolean authorizeUser(AuthDto authDto) {
        try {
            RestTemplate restTemplate = restTemplateConfig.getRestTemplate();
            return restTemplate.postForObject(sessionConfig.getApi() + "authorize", authDto, Boolean.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
