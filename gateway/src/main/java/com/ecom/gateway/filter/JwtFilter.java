package com.ecom.gateway.filter;

import com.ecom.gateway.client.SessionClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class JwtFilter implements GlobalFilter {

    private final SessionClient sessionClient;
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> token = exchange.getRequest().getHeaders().get("Authorization");
        logger.info(exchange.getRequest().getHeaders().toString());
        Boolean isAuthorized = sessionClient
                .authenticateJwtToken(Objects.requireNonNull
                        (exchange.getRequest().getHeaders().get("Authorization")
                ).toString());

        if(isAuthorized){
            return chain.filter(exchange);
        }
        return null;
    }

}

