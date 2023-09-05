package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Cacheable(value = "sellsByUserId")
    public Flux<Sell> findAllSellsByUserId(String userId) {
        return userRepository.
                existsById(userId).
                flatMapMany(
                        exists -> exists ?
                                ((SellRepository) repository).findAllSellsByUserId(userId) :
                                Flux.error(new DataNotFoundException("User not found for id "+ userId))).cache();
    }
    @Cacheable(value = "sellsByStoreId")
    public Flux<Sell> findAllSellsByStoreId(String storeId) {
        return storeRepository.
                existsById(storeId).
                flatMapMany(
                        exists -> exists ?
                            ((SellRepository) repository).findAllSellsByStoreId(storeId) :
                                Flux.error(new DataNotFoundException("Store not found for id "+ storeId))).cache();
    }
    @Override
    public Mono<Sell> save(Sell sell) throws Exception {

        if(sell.getItems().stream().
                anyMatch(item -> !item.getStore().
                        getId().equals(sell.
                                getStore().getId()))){
            throw new DataInconsistencyException("Not all items are related to the store");
        }

        final String storeId = sell.getStore().getId();
        final String userId = sell.getBuyer().getId();

        return Mono.zip(
            storeRepository.existsById(storeId),
            userRepository.existsById(userId)
        ).
        flatMap((tuple) -> {
            try {

                final Boolean existsStore = tuple.getT1(), existsUser = tuple.getT2();

                if(existsStore && existsUser) {
                    return super.save(sell);
                }

                final String message =
                        (!existsStore ? "Store not found for id "+ storeId : "") +
                        (!existsUser ? "User not found for id "+ userId : "");

                return Mono.error(new DataNotFoundException(message));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
