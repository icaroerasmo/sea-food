package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class StoreService extends Service<Store> {
    public Flux<Store> findAllStoresByNamePrefix(String namePrefix) {
        return ((StoreRepository) repository).findAllStoresByNamePrefix(namePrefix);
    }
}
