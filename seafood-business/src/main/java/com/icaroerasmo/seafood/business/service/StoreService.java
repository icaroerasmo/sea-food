package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class StoreService extends Service<Store> {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Cacheable(value = "storeById", key = "#id")
    public Mono<Store> findById(String id) {
        return super.findById(id);
    }
    @Cacheable(value="storesByNamePrefix", key="#namePrefix")
    public Flux<Store> findAllStoresByNamePrefix(String namePrefix) {
        return ((StoreRepository) repository).findAllStoresByNamePrefix(namePrefix).cache();
    }
    @Override
    public Mono<Store> save(Store store) throws Exception {
        final String userId = store.getStoreInfo().getId();
        return userRepository.
                existsById(userId).
                flatMap(exists -> {
                    try {
                        if(exists) {
                            return super.save(store);
                        }
                        return Mono.error(new DataNotFoundException("User not found for id "+userId));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
