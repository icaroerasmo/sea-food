package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.springframework.test.util.AssertionErrors.*;

public class ItemRepositoryTests extends SeafoodCoreApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Test
    void itemPersistenceTest() {
        Item item = userRepository.save(TestMassUtil.user()).
                flatMap(usr -> storeRepository.save(TestMassUtil.store(usr))).
                flatMap(stor -> itemRepository.save(TestMassUtil.item(stor))).
                block();

        Mono<Item> monoItem = itemRepository.findOne(Example.of(item));

        StepVerifier
                .create(monoItem)
                .assertNext(_item -> {
                    assertNotNull("item id is null", _item.getId());
                    assertNotNull("item description is null", _item.getDescription());
                    assertNotEmpty("item description is empty", _item.getDescription());
                    assertNotNull("item store is null", _item.getStore());
                    assertNotNull("item store id is null", _item.getStore().getId());
                    assertNotEmpty("item store id is empty", _item.getStore().getId());
                    assertNotNull("item store score is null", _item.getStore().getScore());
                    assertTrue("item store score is not bigger than zero", _item.getStore().getScore() > 0);
                })
                .expectComplete()
                .verify();

    }
}
