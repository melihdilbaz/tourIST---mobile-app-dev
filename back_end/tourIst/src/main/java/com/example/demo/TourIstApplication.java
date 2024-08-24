package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.controller","com.example.service", "com.example.demo", "com.example.model"})
@EnableMongoRepositories(basePackages = "com.example.repo")
public class TourIstApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourIstApplication.class, args);
	}
	
	

}
