package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.common.CustomDocumentBaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CustomDocumentBaseRepository<Item>, ReactiveMongoRepository<Item, String>, CustomItemRepository {
}
