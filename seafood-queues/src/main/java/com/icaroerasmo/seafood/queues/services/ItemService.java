package com.icaroerasmo.seafood.queues.services;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class ItemService extends Service<Item> {
    @Override
    public Mono<Item> save(Item item) {
        Mono<DocumentBase> docBaseMono =
                ((ItemRepository) repository).
                        getDocumentBaseDataById(item.getId(), Item.class);
        return super.save(item, docBaseMono);
    }
}
