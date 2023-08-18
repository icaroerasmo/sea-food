package com.icaroerasmo.seafood.queues.services;

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

    public Mono<T> save(@Validated T t) {
        return repository.save(t);
    }

    public Mono<Void> delete(@Validated T t) {
        return repository.delete(t);
    }
}
