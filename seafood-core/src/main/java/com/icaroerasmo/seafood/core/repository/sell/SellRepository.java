package com.icaroerasmo.seafood.core.repository.sell;

import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.common.DocumentBaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRepository extends ReactiveMongoRepository<Sell, String>, DocumentBaseRepository<Sell>, CustomSellRepository {
}
