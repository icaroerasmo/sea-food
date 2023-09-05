package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
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
public class StoreService extends Service<Store> {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Cacheable(value = "storeById", key = "#id")
    public Mono<Store> findById(String id) {
        return super.findById(id);
    }
    @Cacheable(value="storesByNamePrefix", key="#namePrefix")
    public Flux<Store> findAllStoresByNamePrefix(String namePrefix) {
        return ((StoreRepository) repository).findAllStoresByNamePrefix(namePrefix).cache();
    }
    @Override
    public Mono<Store> save(Store store) throws Exception {
        final String userId = store.getStoreInfo().getId();
        return userRepository.
                existsById(userId).
                flatMap(exists -> {
                    try {
                        if(exists) {
                            return super.save(store);
                        }
                        return Mono.error(new DataNotFoundException("User not found for id "+userId));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    @Caching(evict = {
        @CacheEvict(value = "storeById", allEntries = true),
        @CacheEvict(value = "storesByNamePrefix", allEntries = true)
    })
    @Scheduled(fixedRate = 180000)
    void cacheCleaning() {
        log.info("Cleaning store cache");
    }
}
