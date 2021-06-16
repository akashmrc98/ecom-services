package com.ecom.gateway.api;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.session")
public class SessionConfig {
    private String host;
    private String api;
    public String getApi(){
        return host + api;
    }
}