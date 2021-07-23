package com.ecom.gateway.filter;

import com.ecom.gateway.client.SessionClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Configuration
@AllArgsConstructor
public class JwtFilter implements GlobalFilter {

    private final SessionClient sessionClient;
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).toString();
        Boolean isAuthorized = sessionClient.authenticateJwtToken(token);

        log.info(exchange.getRequest().toString());

        if(isAuthorized)
            return chain.filter(exchange);

        return null;
    }

}

