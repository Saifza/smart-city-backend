package com.smartcity.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
    	        System.out.println("Discovery Server is starting...before");
    	        SpringApplication.run(DiscoveryServerApplication.class, args);
    	        System.out.println("Discovery Server is starting...after");
    }
}
