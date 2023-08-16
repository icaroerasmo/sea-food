package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface ItemRepository extends ReactiveMongoRepository<Item, String>, CustomItemRepository {
}
