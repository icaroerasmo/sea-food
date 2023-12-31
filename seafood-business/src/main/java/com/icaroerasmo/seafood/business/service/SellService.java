package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Override
    @Cacheable(value = "sellById", key = "#id")
    public Mono<Sell> findById(String id) {
        return super.findById(ObjectUtils.requireNonEmpty(id, "Id is empty"));
    }
    @Cacheable(value = "sellsByUserId", key = "#userId")
    public Flux<Sell> findAllSellsByUserId(String userId) {
        return userRepository.
                existsById(ObjectUtils.requireNonEmpty(userId, "User id is empty")).
                flatMapMany(
                        exists -> exists ?
                                ((SellRepository) repository).findAllSellsByUserId(userId) :
                                Flux.error(new DataNotFoundException("User not found for id "+ userId))).cache();
    }
    @Cacheable(value = "sellsByStoreId", key = "#storeId")
    public Flux<Sell> findAllSellsByStoreId(String storeId) {
        return storeRepository.
                existsById(ObjectUtils.requireNonEmpty(storeId, "Store id is empty")).
                flatMapMany(
                        exists -> exists ?
                            ((SellRepository) repository).findAllSellsByStoreId(ObjectUtils.requireNonEmpty(storeId)) :
                                Flux.error(new DataNotFoundException("Store not found for id "+ storeId))).cache();
    }
    @Override
    public Mono<Sell> save(@Valid Sell sell) throws Exception {

        if(sell.getItems().stream().
                anyMatch(item -> !item.getStore().
                        getId().equals(sell.
                                getStore().getId()))){
            throw new DataInconsistencyException("Not all items are related to the store");
        }

        final String storeId = sell.getStore().getId();
        final String userId = sell.getBuyer().getId();

        return Mono.zip(
            storeRepository.existsById(storeId),
            userRepository.existsById(userId)
        ).
        flatMap((tuple) -> {
            try {

                final Boolean existsStore = tuple.getT1(), existsUser = tuple.getT2();

                if(existsStore && existsUser) {
                    return super.save(sell);
                }

                final String message =
                        (!existsStore ? "Store not found for id "+ storeId : "") +
                        (!existsUser ? "User not found for id "+ userId : "");

                return Mono.error(new DataNotFoundException(message));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Caching(evict = {
        @CacheEvict(value = "sellById", allEntries = true),
        @CacheEvict(value = "sellsByUserId", allEntries = true),
        @CacheEvict(value = "sellsByStoreId", allEntries = true)
    })
    @Scheduled(fixedRateString = "#{@cacheProperties.getTtl()}")
    void cacheCleaning() {
        log.info("Cleaning sell cache");
    }
}
