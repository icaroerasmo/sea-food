package com.icaroerasmo.seafood.queues.services;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.repository.common.DocumentBaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Log4j2
public abstract class Service<T extends DocumentBase> {
    @Autowired
    protected ReactiveMongoRepository<T, String> repository;
    @Transactional
    public Mono<T> save(T t) {

        final DocumentBaseRepository<T> docBasRepo = (DocumentBaseRepository<T>) repository;

        if (t.getId() != null) {
            return docBasRepo.
                    getDocumentBaseDataById(t).
                    flatMap(saved -> {
                        if (saved != null) {
                            t.setCreatedAt(saved.getCreatedAt());
                        }
                        return repository.save(t);
                    });
        }
        return repository.save(t);
    }
    @Transactional
    public Mono<Void> delete(T t) {
        return repository.delete(t);
    }
}
