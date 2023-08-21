package com.icaroerasmo.seafood;

import com.icaroerasmo.seafood.api.service.UserService;
import com.icaroerasmo.seafood.core.enums.AddressType;
import com.icaroerasmo.seafood.core.model.Address;
import com.icaroerasmo.seafood.core.model.IndividualPerson;
import com.icaroerasmo.seafood.core.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@EnableKafka
@EnableWebFlux
@EnableMongoAuditing
@SpringBootApplication
@EnableReactiveMongoRepositories
public class SeaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaFoodApplication.class, args);
	}
}
