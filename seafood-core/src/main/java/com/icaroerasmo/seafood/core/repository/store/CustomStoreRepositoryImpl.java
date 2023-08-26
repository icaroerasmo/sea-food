package com.icaroerasmo.seafood.core.repository.store;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

public class CustomStoreRepositoryImpl implements CustomStoreRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Store> findAllStoresByNamePrefix(String namePrefix) {

        if(namePrefix.length() < 3) {
            return Flux.empty();
        }

        return mongoTemplate.find(QueryUtil.queryByPrefix("storeInfo.userInfo.name", namePrefix), Store.class);
    }
}
