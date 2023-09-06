package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.common.DocumentBaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends DocumentBaseRepository<Item>, ReactiveMongoRepository<Item, String>, CustomItemRepository {
}
