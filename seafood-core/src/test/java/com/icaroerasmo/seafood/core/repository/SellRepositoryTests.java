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
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple5;

import java.util.Arrays;

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

        final User buyer = TestMassUtil.user();
        buyer.getUserInfo().setEmail("buyer@buyer.com");
        buyer.getUserInfo().setDocumentNo("1111111111");

        final User storeUser = TestMassUtil.user();

        final Mono<User> buyerMono = userRepository.save(buyer);

        final Mono<Store> storeMono = userRepository.
                save(storeUser).
                flatMap(usr ->
                        storeRepository.
                                save(TestMassUtil.store(usr)));

        Mono<Tuple5<Store,User,Item, Item, Item>> storItemsMono =
            storeMono.flatMap(stor -> {
                Item item1 = TestMassUtil.item(stor);
                item1.setDescription("Item 1");

                Item item2 = TestMassUtil.item(stor);
                item2.setDescription("Item 2");

                Item item3 = TestMassUtil.item(stor);
                item3.setDescription("Item 3");

                return Mono.zip(
                        Mono.just(stor),
                        buyerMono,
                        itemRepository.save(item1),
                        itemRepository.save(item2),
                        itemRepository.save(item3)
                );
            });

        Mono<Sell> sellMono = storItemsMono.flatMap(tuple -> {
            Sell sell = new Sell();
            sell.setStore(tuple.getT1());
            sell.setBuyer(tuple.getT2());
            sell.setItems(Arrays.asList(
                    tuple.getT3(),
                    tuple.getT4(),
                    tuple.getT5()
            ));
            return sellRepository.save(sell);
        });
    }
}
