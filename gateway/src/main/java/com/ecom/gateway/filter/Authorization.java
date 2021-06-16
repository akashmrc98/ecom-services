package com.ecom.gateway.filter;

import com.ecom.gateway.api.SessionConfig;
import com.ecom.gateway.client.SessionClient;
import com.ecom.gateway.dto.AuthDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Configuration
@AllArgsConstructor
public class Authorization implements GlobalFilter {

    private final SessionClient sessionClient;

    private static final Logger logger = LoggerFactory.getLogger(Authorization.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        logger.info(headers.toString());
//        try{
//            AuthDto authDto = new AuthDto();
//            authDto.setAccessToken(headers.get("Authorization").toString());
//            System.out.println(headers.get("Authorization").toString());
//            boolean isAuthorized = sessionClient.authorizeUser(authDto);
//            System.out.println(headers.toString());
//            if (isAuthorized)
//                return chain.filter(exchange);
//            return null;
//        } catch (NullPointerException npe){
//            System.out.println(headers.get("Authorization").toString());
//            return null;
//        }
        return chain.filter(exchange);
    }
}
