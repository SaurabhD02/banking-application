package com.spd.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    // Define the list of public endpoints
    private static final List<String> PUBLIC_ENDPOINTS = List.of("/api/auth/signUp", "/api/auth/login",
            "/actuator/refresh");

    public Predicate<ServerHttpRequest> isSecured = request -> PUBLIC_ENDPOINTS.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
