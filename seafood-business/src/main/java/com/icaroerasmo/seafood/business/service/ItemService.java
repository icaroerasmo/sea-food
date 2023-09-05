package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class ItemService extends Service<Item> {
    @Autowired
    private StoreRepository storeRepository;
    @Override
    @Cacheable(value = "itemById", key = "#id")
    public Mono<Item> findById(String id) {
        return super.findById(id);
    }
    @Cacheable(value = "itemsByDescriptionPrefix", key = "#descriptionPrefix")
    public Flux<Item> findAllItemsByDescriptionPrefix(String descriptionPrefix) {
        return ((ItemRepository) repository).findAllItemsByDescriptionPrefix(descriptionPrefix).cache();
    }
    @Cacheable(value = "itemsByStore", key = "#storeId")
    public Flux<Item> findAllItemsByStoreId(String storeId) {
        return storeRepository.
                existsById(storeId).
                flatMapMany(
                        exists -> exists ?
                        ((ItemRepository) repository).findAllItemsByStoreId(storeId) :
                            Flux.error(new DataNotFoundException("Item not found for id "+ storeId))).cache();
    }
    @Override
    public Mono<Item> save(Item item) throws Exception {
        final String storeId = item.getStore().getId();
        return storeRepository.
                existsById(item.getStore().getId()).
                flatMap(
                        exists -> {
                            try {
                                if(exists) {
                                    return super.save(item);
                                }
                                return Mono.error(new DataNotFoundException("Item not found for id "+ storeId));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
    }
}
