package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

@Log4j2
public abstract class Service<T extends DocumentBase> {
    @Autowired
    protected ReactiveMongoRepository<T, String> repository;
    @Autowired
    private KafkaService kafkaService;
    public Mono<T> save(T t) throws Exception {
        return Mono.just(kafkaService.send(KafkaOperation.SAVE, t));
    }
    public Mono<T> delete(String id) throws Exception {
        return repository.findById(id).
                switchIfEmpty(
                        Mono.error(
                                new DataNotFoundException("Document not found for ID: "+ id)
                        )).
                doOnSuccess((t) -> {
                    try {
                        kafkaService.send(KafkaOperation.DELETE, t);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    @Cacheable(value = "byId", key = "#id")
    public Mono<T> findById(String id) {
        return repository.findById(id).switchIfEmpty(
                Mono.error(new DataNotFoundException("Document not found for ID: "+ id))).cache();
    }
}
