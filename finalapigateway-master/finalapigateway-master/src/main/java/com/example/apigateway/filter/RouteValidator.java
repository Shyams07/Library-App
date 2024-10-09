package com.example.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // List of open API endpoints (public endpoints)
    public static final List<String> openApiEndpoints = List.of(
            "/auth/login",
            "/auth/register",
            "/user/na/**",
            "/eureka/**"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // Predicate to determine if a request is secured or not
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> pathMatcher.match(uri, request.getURI().getPath()));
}
