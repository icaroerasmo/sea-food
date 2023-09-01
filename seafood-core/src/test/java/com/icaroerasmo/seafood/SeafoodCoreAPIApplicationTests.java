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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Log4j2
@SpringBootTest
class SeafoodCoreAPIApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StoreRepository storeRepository;

	@Test
	void contextLoads() {
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
				doOnError((err) -> Mono.error(err)).
				flatMap(usr -> {
					log.error(usr);
					store.setStoreInfo(usr);
					return storeRepository.save(store);
				}).
				doOnError((err) -> Mono.error(err)).
				subscribe((result) -> log.info(result));
	}

}
