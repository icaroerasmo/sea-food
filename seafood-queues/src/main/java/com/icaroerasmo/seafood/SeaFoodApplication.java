package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@Log4j2
@EnableKafka
@EnableMongoAuditing
@SpringBootApplication
@EnableReactiveMongoRepositories
public class SeaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaFoodApplication.class, args);
	}
}
