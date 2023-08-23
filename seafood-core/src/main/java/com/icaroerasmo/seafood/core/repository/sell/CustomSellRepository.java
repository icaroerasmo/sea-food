package com.icaroerasmo.seafood.core.repository.sell;

import com.icaroerasmo.seafood.core.model.Sell;
import reactor.core.publisher.Flux;

public interface CustomSellRepository {
    Flux<Sell> findAllSellsByUserId(String id);
    Flux<Sell> findAllSellsByStoreId(String id);
}
