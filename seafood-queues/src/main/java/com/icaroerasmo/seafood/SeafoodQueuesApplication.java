package com.icaroerasmo.seafood;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@Log4j2
@EnableKafka
@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
public class SeafoodQueuesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeafoodQueuesApplication.class, args);
	}
}
