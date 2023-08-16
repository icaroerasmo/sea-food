package com.icaroerasmo.seafood.core.repository.sell;

import com.icaroerasmo.seafood.core.model.Sell;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomSellRepository {
    Mono<Sell> findSellById(String id);
    Flux<Sell> findAllSellsByUserId(String id);
    Flux<Sell> findAllSellsByStoreId(String id);
}
