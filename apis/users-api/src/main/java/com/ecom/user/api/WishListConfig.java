package com.ecom.user.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.wishlist")
public class WishListConfig {
    private String host;
    private String api;
    public String getApi(){
        return host + api;
    }
}
