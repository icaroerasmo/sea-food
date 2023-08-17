package com.icaroerasmo.seafood;

import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SeaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeaFoodApplication.class, args);
	}

}
