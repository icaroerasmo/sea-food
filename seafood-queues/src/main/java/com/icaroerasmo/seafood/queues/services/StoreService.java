package com.icaroerasmo.seafood.queues.services;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.repository.store.StoreRepository;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class StoreService extends Service<Store> {

    @Override
    public Mono<Store> save(Store store) {
        Mono<DocumentBase> docBaseMono =
                ((StoreRepository) repository).
                        getDocumentBaseDataById(store.getId(), Store.class);
        return super.save(store, docBaseMono);
    }
}
