package com.icaroerasmo.seafood.queues.services;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.model.Sell;
import com.icaroerasmo.seafood.core.repository.sell.SellRepository;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class SellService extends Service<Sell> {
    @Override
    public Mono<Sell> save(Sell sell) {
        Mono<DocumentBase> docBaseMono =
                ((SellRepository) repository).
                        getDocumentBaseDataById(sell.getId(), Sell.class);
        return super.save(sell, docBaseMono);
    }
}
