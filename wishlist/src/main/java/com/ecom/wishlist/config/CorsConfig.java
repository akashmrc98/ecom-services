package com.ecom.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                try {
                    registry.addMapping("/**")
                            .allowedOrigins("*")
                            .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                            .allowedHeaders("*");
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        };
    }
}
