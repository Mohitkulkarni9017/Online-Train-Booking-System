package com.mohit.railway.searching.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mohit.railway.searching.service", "com.mohit.railway.searching.service.controller", "com.mohit.railway.otherpackage"})
@EnableDiscoveryClient
//@CrossOrigin(origins="http://localhost:4200")
public class RailwaySearchingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwaySearchingServiceApplication.class, args);
	}

	
}
