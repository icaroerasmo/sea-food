package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
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
}
