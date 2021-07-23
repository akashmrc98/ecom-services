package com.ecom.user.client;

import com.ecom.user.config.RestTemplateConfig;
import com.ecom.user.dto.SessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionClient {

    private final RestTemplateConfig restTemplateConfig;

    public Boolean registerSessionService(SessionDto sessionDto){
        return restTemplateConfig
                .getRestTemplate()
                .postForObject("http://localhost:8081/api/v1/sessions/", sessionDto, Boolean.class);
    }
}
