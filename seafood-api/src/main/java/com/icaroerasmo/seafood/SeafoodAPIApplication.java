package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.config.EnableWebFlux;

@Log4j2
@EnableKafka
@EnableWebFlux
@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
public class SeafoodAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeafoodAPIApplication.class, args);
	}
}
