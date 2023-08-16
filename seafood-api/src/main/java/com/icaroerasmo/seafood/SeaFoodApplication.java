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
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
//@EnableWebFlux
@SpringBootApplication
@EnableReactiveMongoRepositories
public class SeaFoodApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

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
		CompletableFuture<User> usrComp = new CompletableFuture<>();
		userService.save(user).subscribe(result -> {
			log.info("user {} saved!", result.getId());
			usrComp.complete(result);
		});

		CompletableFuture.runAsync(() -> {
			try {
				User user1 = usrComp.get();
				System.out.println(user1.getUserInfo().getName());
				userService.findById(user1.getId()).subscribe((result) -> System.out.println(result.getId()));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
