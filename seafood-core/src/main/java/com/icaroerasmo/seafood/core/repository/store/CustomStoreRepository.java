package com.icaroerasmo.seafood.core.repository.store;

import com.icaroerasmo.seafood.core.model.Store;
import reactor.core.publisher.Flux;

public interface CustomStoreRepository {
    Flux<Store> findAllStoresByNamePrefix(String namePrefix);
}
