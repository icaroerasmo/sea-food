package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.SeafoodCoreApplicationTests;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.TestMassUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple5;

import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class SellRepositoryTests extends SeafoodCoreApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SellRepository sellRepository;
    @Test
    void sellPersistenceTest() {

        final User storeUser = TestMassUtil.user();

        final Mono<Store> storeMono = userRepository.
                save(storeUser).
                flatMap(usr ->
                        storeRepository.
                                save(TestMassUtil.store(usr)));

        Mono<Tuple5<Store,User,Item, Item, Item>> storItemsMono =
            storeMono.flatMap(stor -> {

                User buyer = TestMassUtil.user();
                buyer.getUserInfo().setEmail("buyer@buyer.com");
                buyer.getUserInfo().setDocumentNo("1111111111");

                Item item1 = TestMassUtil.item(stor);
                item1.setDescription("Item 1");

                Item item2 = TestMassUtil.item(stor);
                item2.setDescription("Item 2");

                Item item3 = TestMassUtil.item(stor);
                item3.setDescription("Item 3");

                return Mono.zip(
                        Mono.just(stor),
                        userRepository.save(buyer),
                        itemRepository.save(item1),
                        itemRepository.save(item2),
                        itemRepository.save(item3)
                );
            });

        Sell sell = storItemsMono.flatMap(tuple -> {
            Sell _sell = new Sell();
            _sell.setStore(tuple.getT1());
            _sell.setBuyer(tuple.getT2());
            _sell.setItems(Arrays.asList(
                    tuple.getT3(),
                    tuple.getT4(),
                    tuple.getT5()
            ));
            return sellRepository.save(_sell);
        }).block();

        Mono<Sell> monoSell = sellRepository.findOne(Example.of(sell));

        StepVerifier
                .create(monoSell)
                .assertNext(_sell -> {
                    assertNotNull("sell id is null", _sell.getId());
                    assertNotNull("sell buyer is null", _sell.getBuyer());
                    assertNotNull("sell buyer id is null", _sell.getBuyer().getId());
                    assertNotNull("sell buyer name is null", _sell.getBuyer().getUserInfo().getName());
                    assertNotNull("sell store is null", _sell.getStore());
                    assertNotNull("sell item list is null", _sell.getItems());
                    assertFalse("sell item list is empty", _sell.getItems().isEmpty());
                    assertNotNull("sell first item from list description is null", _sell.getItems().get(0).getDescription());
                })
                .expectComplete()
                .verify();
    }
}
