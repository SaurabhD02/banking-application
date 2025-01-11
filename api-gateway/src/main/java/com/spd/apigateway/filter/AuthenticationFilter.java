package com.spd.apigateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.spd.apigateway.exception.UnAuthorizedUserException;
import com.spd.apigateway.util.JwtUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Conf> {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private RouteValidator validator;

    private JwtUtil jwtUtil;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public String getJwtFromHeader(HttpHeaders headers) {
        String authorizationHeader =headers.getFirst(AUTHORIZATION);
        String token = null;
        if (null != authorizationHeader && authorizationHeader.startsWith(BEARER)) {
            token = authorizationHeader.substring(7);
        } else {
            LOGGER.error("missing authorization header");
            throw new UnAuthorizedUserException("missing authorization header");
        }
        return token;
    }

    public static class Conf {

    }

    @Override
    public GatewayFilter apply(Conf config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                String token = getJwtFromHeader(exchange.getRequest().getHeaders());
                if (token != null && !token.isEmpty()) {
                    boolean isTokenExpired = jwtUtil.isTokenExpired(token);
                    try {
                        jwtUtil.validateToken(token);

                    } catch (Exception e) {
                        LOGGER.error("un authorized access to application");
                        throw new RuntimeException("Unauthorized access of protected resource, Invalid credentials");
                    }
                }
            }
            return chain.filter(exchange);
        });
    }
}
