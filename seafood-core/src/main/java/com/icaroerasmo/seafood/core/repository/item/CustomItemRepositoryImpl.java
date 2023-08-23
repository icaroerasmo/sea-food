package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public class CustomItemRepositoryImpl implements CustomItemRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Item> findAllItemsByDescriptionPrefix(String descriptionPrefix) {

        if(descriptionPrefix.length() < 3) {
            return Flux.empty();
        }

        return mongoTemplate.find(QueryUtil.queryByPrefix("description", descriptionPrefix), Item.class);
    }

    @Override
    public Flux<Item> findAllItemsByStoreId(String storeId) {
        final Query query = new Query(Criteria.where("store.id").is(storeId));
        return mongoTemplate.find(query, Item.class);
    }
}
