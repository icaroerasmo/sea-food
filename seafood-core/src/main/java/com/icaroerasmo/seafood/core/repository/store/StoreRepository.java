package com.icaroerasmo.seafood.core.repository.store;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.common.DocumentBaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends ReactiveMongoRepository<Store, String>, DocumentBaseRepository<Store>, CustomStoreRepository {
}
