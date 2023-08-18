package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.api.kafka.KafkaResponseManager;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Log4j2
public abstract class Service<T> {

    @Autowired
    protected ReactiveMongoRepository<T, String> repository;
    @Autowired
    private KafkaResponseManager responseManager;
    @Autowired
    private KafkaService producerService;

    public Mono<T> save(@Validated T t) throws Exception {
        final String uuid = producerService.send(KafkaOperation.SAVE, t);
        final Object lock = responseManager.getLock(uuid);
        synchronized(lock) {
            lock.wait();
        }
        return Mono.just(responseManager.retrieve(uuid));
    }

    public Mono<Void> delete(@Validated T t) {
        producerService.send(KafkaOperation.DELETE, t);
        return Mono.empty();
    }

    public Mono<T> findById(String id) {
        return repository.findById(id);
    }
}
