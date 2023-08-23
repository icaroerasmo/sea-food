package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class ItemService extends Service<Item> {
    @Autowired
    private StoreRepository storeRepository;
    public Flux<Item> findAllItemsByDescriptionPrefix(String descriptionPrefix) {
        return ((ItemRepository) repository).findAllItemsByDescriptionPrefix(descriptionPrefix);
    }
    public Flux<Item> findAllItemsByStoreId(String storeId) {
        return storeRepository.
                existsById(storeId).
                flatMapMany(
                        exists -> exists ?
                        ((ItemRepository) repository).findAllItemsByStoreId(storeId) :
                            Flux.error(new DataNotFoundException("Item not found for id "+ storeId)));
    }
}
