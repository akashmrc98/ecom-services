package com.ecom.products.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.review")
@Data
public class ReviewConfig {
        private String host;
        private String api;
        public String getApi(){
            return host + api;
        }
}
