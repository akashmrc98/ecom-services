package com.ecom.gateway.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class SessionConfig {
    private final String host = "http://localhost:8081/";
    private final String api = "api/v1/sessions/";

    public String getApi(){
        return host + api;
    }
}
