package com.example.cgi24_jwth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Cgi24Jwth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cgi24Jwth2Application.class, args);
	}

}
