package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.core.model.Store;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface StoreRepository extends ReactiveMongoRepository<Store, String> {
}
