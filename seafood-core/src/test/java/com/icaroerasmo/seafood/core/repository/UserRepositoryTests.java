package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class UserRepositoryTests extends SeafoodCoreApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    void userPersistenceTest() {
        final User user = TestMassUtil.user();

        userRepository.
                save(user).
                doOnError((err) -> Mono.error(err)).block();

        Mono<User> userMono = userRepository.findOne(Example.of(user));

        StepVerifier
                .create(userMono)
                .assertNext(_user -> {
                    assertNotNull("user id is null", _user.getId());
                    assertNotNull("userInfo is null", _user.getUserInfo());
                    assertNotNull("userInfo name is null", _user.getUserInfo().getName());
                    assertNotEmpty("userInfo name is empty", _user.getUserInfo().getName());
                    assertNotNull("password is null", _user.getPassword());
                    assertNotEmpty("password is empty", _user.getPassword());
                    assertNotNull("createdAt is null", _user.getCreatedAt());
                    assertNotNull("updatedAt is null", _user.getUpdatedAt());
                })
                .expectComplete()
                .verify();
    }
}