package com.icaroerasmo.seafood.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication(scanBasePackages = "com.icaroerasmo.seafood")
public class SeaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaFoodApplication.class, args);
	}

}
