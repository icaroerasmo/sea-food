package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.api.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    public Flux<Sell> findAllSellsByUserId(String id) {
        return ((SellRepository) repository).findAllSellsByUserId(id);
    }
    public Flux<Sell> findAllSellsByStoreId(String id) {
        return ((SellRepository) repository).findAllSellsByStoreId(id);
    }
    @Override
    public Mono<Sell> save(@Validated Sell sell) throws Exception {
        if(sell.getItems().stream().
                anyMatch(item -> !item.getStore().
                        getId().equals(sell.
                                getStore().getId()))){
            throw new DataInconsistencyException("Not all items are related to the store");
        }
        return super.save(sell);
    }
}
