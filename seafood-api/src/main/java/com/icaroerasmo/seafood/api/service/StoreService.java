package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class StoreService extends Service<Store> {
    public Flux<Store> findAllStoresByNamePrefix(String namePrefix) {
        return ((StoreRepository) repository).findAllStoresByNamePrefix(namePrefix);
    }
}
