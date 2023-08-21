package com.icaroerasmo.seafood.core.repository.item;

import com.icaroerasmo.seafood.core.model.Item;
import reactor.core.publisher.Flux;

public interface CustomItemRepository {
    Flux<Item> findAllItemsByDescriptionPrefix(String descriptionPrefix);
    Flux<Item> findAllItemsByStoreId(String storeId);
}
