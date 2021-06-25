package com.example.orders.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(value = "api.product")
public class ProductConfig {
        private String host;
        private String api;
        public String getApi(){
            return host + api;
        }
}
