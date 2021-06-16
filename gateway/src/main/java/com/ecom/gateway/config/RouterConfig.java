package com.ecom.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/v1/sessions/**").uri("http://localhost:8081"))
                .route(r -> r.path("/api/v1/users/**").uri("http://localhost:8082"))
                .route(r -> r.path("/api/v1/products/**").uri("http://localhost:8083"))
                .route(r -> r.path("/api/v1/carts/**").uri("http://localhost:8084"))
                .route(r -> r.path("/api/v1/wishlists/**").uri("http://localhost:8085"))
                .route(r -> r.path("/api/v1/orders/**").uri("http://localhost:8086"))
                .route(r -> r.path("/api/v1/reviews/**").uri("http://localhost:8087"))
                .build();
    }
}
