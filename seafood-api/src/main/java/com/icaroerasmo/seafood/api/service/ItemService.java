package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class ItemService extends Service<Item> {

    public Flux<Item> findAllItemsByDescriptionPrefix(String descriptionPrefix) {
        return ((ItemRepository) repository).findAllItemsByDescriptionPrefix(descriptionPrefix);
    }
}
