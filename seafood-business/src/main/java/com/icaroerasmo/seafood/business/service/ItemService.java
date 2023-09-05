package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.repository.item.ItemRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
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
    @Caching(evict = {
        @CacheEvict(value = "itemByid", allEntries = true),
        @CacheEvict(value = "itemsByDescriptionPrefix", allEntries = true),
        @CacheEvict(value = "itemsByStore", allEntries = true)
    })
    @Scheduled(fixedRateString = "#{@cacheProperties.getTtl()}")
    void cacheCleaning() {
        log.info("Cleaning item cache");
    }
}
