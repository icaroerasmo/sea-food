package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@Log4j2
@EnableKafka
@EnableCaching
@EnableWebFlux
@EnableScheduling
@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
public class SeafoodGraqphQlAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeafoodGraqphQlAPIApplication.class, args);
	}
}
