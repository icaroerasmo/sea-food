package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
                    assertTrue("store score is not bigger than zero", _store.getScore() > 0);
                    assertNotNull("storeInfo is null", _store.getStoreInfo());
                    assertNotNull("userInfo is null" , _store.getStoreInfo().getId());
                    assertEquals("userInfo is not equal", "test test", _store.getStoreInfo().getUserInfo().getName());
                })
                .expectComplete()
                .verify();
    }
    @Test
    void storePersistenceWithSameEmailTest() {
        User user1 = TestMassUtil.user();
        user1.setId("1");
        final Store store1 = TestMassUtil.store(user1);

        User user2 = TestMassUtil.user();
        user2.setId("2");
        user2.getUserInfo().setDocumentNo("1111111111");
        final Store store2 = TestMassUtil.store(user2);

        Mono<Store> userMono1 = storeRepository.save(store1);
        Mono<Store> userMono2 = storeRepository.save(store2);

        Assertions.assertThrows(DuplicateKeyException.class, () -> Mono.zip(userMono1, userMono2).block());
    }
    @Test
    void storePersistenceWithSameDocumentTest() {
        User user1 = TestMassUtil.user();
        user1.setId("1");
        final Store store1 = TestMassUtil.store(user1);

        User user2 = TestMassUtil.user();
        user2.setId("2");
        user2.getUserInfo().setEmail("i@i.com");
        final Store store2 = TestMassUtil.store(user2);

        Mono<Store> userMono1 = storeRepository.save(store1);
        Mono<Store> userMono2 = storeRepository.save(store2);

        Assertions.assertThrows(DuplicateKeyException.class, () -> Mono.zip(userMono1, userMono2).block());
    }
}
