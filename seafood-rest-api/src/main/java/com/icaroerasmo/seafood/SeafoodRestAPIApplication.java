package com.icaroerasmo.seafood;

import com.icaroerasmo.seafood.core.enums.AddressType;
import com.icaroerasmo.seafood.core.enums.PersonType;
import com.icaroerasmo.seafood.core.model.Address;
import com.icaroerasmo.seafood.core.model.Person;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Log4j2
@EnableKafka
@EnableWebMvc
@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
public class SeafoodRestAPIApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StoreRepository storeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SeafoodRestAPIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Address address = new Address();
		address.setAddressType(AddressType.AVENUE);
		address.setAddress("Tancredo Neves");
		address.setNumber("148");
		address.setComplement("Loja Casas Bahia");

		Person person = new Person();
		person.setPersonType(PersonType.LEGAL_ENTITY);
		person.setDocumentNo("987654321");
		person.setName("test test");
		person.setEmail("t@t.com");
		person.setPhone("+5571998655665");
		person.setAddresses(Arrays.asList(address));

		User user = new User();
		user.setPassword("P@ssw0rd!");
		user.setUserInfo(person);

		Store store = new Store();
		store.setScore(4.95F);

		userRepository.
		save(user).
		flatMap(usr -> {
			store.setStoreInfo(usr);
			log.info(store);
			return storeRepository.save(store);
		}).
		doOnNext(_store -> log.info(_store)).
		switchIfEmpty(Mono.error(new RuntimeException("Validation failed"))).
		subscribe((result) -> log.info(result));

//		storeRepository.findById("64e81b789314511278c6f19e").
//				subscribe(_store -> {
//					log.info(_store);
//				});
	}
}
