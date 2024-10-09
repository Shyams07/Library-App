package com.example.cgi24eurekaserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Cgi24EurekaserverApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        // This test ensures that the application context loads successfully.
    }

    @Test
    void eurekaServerIsUp() {
        // Test to verify that the Eureka server is running and responds with a 200 OK status.
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/eureka/apps", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
