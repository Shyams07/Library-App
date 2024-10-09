package com.example.cgi24eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class Cgi24EurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cgi24EurekaserverApplication.class, args);
	}

}
