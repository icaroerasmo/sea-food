package com.icaroerasmo.seafood.core.repository.sell;

import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomSellRepositoryImpl implements CustomSellRepository {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    @Override
    public Flux<Sell> findAllSellsByUserId(String id) {
        Query query = new Query(Criteria.where("user.id").is(id));
        return mongoTemplate.find(query, Sell.class);
    }
    @Override
    public Flux<Sell> findAllSellsByStoreId(String id) {
        Query query = new Query(Criteria.where("store.id").is(id));
        return mongoTemplate.find(query, Sell.class);
    }
}
