package com.icaroerasmo.seafood.core.repository.sell;

import com.icaroerasmo.seafood.core.model.Sell;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface SellRepository extends ReactiveMongoRepository<Sell, String>, CustomSellRepository {
}