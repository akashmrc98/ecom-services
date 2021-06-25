package com.ecom.gateway.client;

import com.ecom.gateway.config.SessionConfig;
import com.ecom.gateway.dto.AuthDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class SessionClient {

    private final SessionConfig sessionConfig;

    public Boolean authenticateJwtToken(String token) {
        AuthDto authDto = new AuthDto();
        authDto.setAccessToken(token);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(sessionConfig.getApi() + "authorize", authDto, Boolean.class);
    }
}
