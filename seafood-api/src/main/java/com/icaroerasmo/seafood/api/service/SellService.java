package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    private SellRepository _repository = (SellRepository) repository;

    public Mono<Sell> findSellById(String id) {
        return _repository.findSellById(id);
    }

    public Flux<Sell> findAllSellsByUserId(String id) {
        return _repository.findAllSellsByUserId(id);
    }

    public Flux<Sell> findAllSellsByStoreId(String id) {
        return _repository.findAllSellsByStoreId(id);
    }
}
