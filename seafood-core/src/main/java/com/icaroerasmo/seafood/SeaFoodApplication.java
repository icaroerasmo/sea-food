package com.icaroerasmo.seafood;

import com.icaroerasmo.seafood.core.enums.AddressType;
import com.icaroerasmo.seafood.core.model.Address;
import com.icaroerasmo.seafood.core.model.IndividualPerson;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SeaFoodApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SeaFoodApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final String password = "P@55w0rd123!";
		final Address address = new Address(
				AddressType.AVENUE,
				"Wall Street",
				"1053"
		);
		final IndividualPerson individualPerson = new IndividualPerson(
				"User User",
				"123456789",
				"user@user.com",
				"123456789",
				Arrays.asList(address));

		final User user = new User(password, individualPerson);
		userRepository.save(user);
	}

}
