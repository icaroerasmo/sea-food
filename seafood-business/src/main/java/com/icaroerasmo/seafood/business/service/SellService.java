package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    public Flux<Sell> findAllSellsByUserId(String userId) {
        return userRepository.
                existsById(userId).
                flatMapMany(exists -> {
                    if(exists) {
                        return ((SellRepository) repository).
                                    findAllSellsByUserId(userId);
                    }
                    return Flux.error(new DataNotFoundException("User not found for id "+ userId));
                });
    }
    public Flux<Sell> findAllSellsByStoreId(String storeId) {
        return storeRepository.
                existsById(storeId).
                flatMapMany(exists -> {
                    if(exists) {
                        return ((SellRepository) repository).
                                    findAllSellsByStoreId(storeId);
                    }
                    return Flux.error(new DataNotFoundException("Store not found for id "+ storeId));
                });
    }
    @Override
    public Mono<Sell> save(Sell sell) throws Exception {
        if(sell.getItems().stream().
                anyMatch(item -> !item.getStore().
                        getId().equals(sell.
                                getStore().getId()))){
            throw new DataInconsistencyException("Not all items are related to the store");
        }
        return super.save(sell);
    }
}
