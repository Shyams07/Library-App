package com.example.apigateway;

import com.example.apigateway.filter.AuthenticationFilter;
import com.example.apigateway.filter.RouteValidator;
import com.example.apigateway.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApigatewayApplicationTests {

    @InjectMocks
    private AuthenticationFilter authenticationFilter;

    @Mock
    private RouteValidator routeValidator;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        // Test to ensure the application context loads successfully.
    }
    
    @Test
    void testRouteValidatorSecuredEndpoint() {
        // Test to verify that the secured endpoint is correctly identified as secured.
        RouteValidator validator = new RouteValidator();
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/secured-endpoint")
                .build();

        boolean isSecured = validator.isSecured.test(request);
        assertTrue(isSecured);
    }

    @Test
    void testRouteValidatorPublicEndpoint() {
        // Test to verify that the public endpoint is correctly identified as not secured.
        RouteValidator validator = new RouteValidator();
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/auth/login")
                .build();

        boolean isSecured = validator.isSecured.test(request);
        assertFalse(isSecured);
    }

    @Test
    void testJwtUtilInvalidToken() {
        // Test to ensure an exception is thrown when validating an invalid JWT token.
        JwtUtil jwtUtil = new JwtUtil();
        String invalidToken = "invalid.token.here";
        assertThrows(Exception.class, () -> jwtUtil.validateToken(invalidToken));
    }

    @Test
    void testRouteValidatorSecuredEndpointShouldFail() {
        // This test is expected to fail since we're incorrectly asserting that a secured endpoint is public.
        RouteValidator validator = new RouteValidator();
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/secured-endpoint")
                .build();

     
        boolean isSecured = validator.isSecured.test(request);
        assertFalse(isSecured);
    }
}
