package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.springframework.test.util.AssertionErrors.*;

public class StoreRepositoryTests extends SeafoodCoreApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Test
    void storePersistenceTest() {
        final User user = TestMassUtil.user();

        final Store store = new Store();
        store.setScore(4.95F);

        userRepository.
                save(user).
                doOnError((err) -> Mono.error(err)).
                flatMap(usr -> {
                    store.setStoreInfo(usr);
                    return storeRepository.save(store);
                }).block();

        Mono<Store> storeMono = storeRepository.findOne(Example.of(store));

        StepVerifier
                .create(storeMono)
                .assertNext(_store -> {
                    assertNotNull("store id is null", _store.getId());
                    assertNotNull("store score is null", _store.getScore());
                    assertTrue("store score is not bigger than zero", store.getScore() > 0);
                    assertNotNull("storeInfo is null", _store.getStoreInfo());
                    assertNotNull("userInfo is null" , _store.getStoreInfo().getId());
                    assertEquals("userInfo is not equal", "test test", _store.getStoreInfo().getUserInfo().getName());
                })
                .expectComplete()
                .verify();
    }
}
