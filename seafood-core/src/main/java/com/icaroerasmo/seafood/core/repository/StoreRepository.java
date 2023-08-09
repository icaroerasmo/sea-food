package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.core.model.Store;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends ReactiveMongoRepository<Store, String> {
}
