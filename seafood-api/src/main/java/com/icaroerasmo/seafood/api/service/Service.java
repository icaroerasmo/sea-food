package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.api.exceptions.DataInconsistencyException;
import com.icaroerasmo.seafood.api.kafka.KafkaResponseManager;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Log4j2
public abstract class Service<T extends DocumentBase> {
    @Autowired
    protected ReactiveMongoRepository<T, String> repository;
    @Autowired
    private KafkaResponseManager responseManager;
    @Autowired
    private KafkaService kafkaService;
    public Mono<T> save(@Validated T t) throws Exception {
        final String uuid = UUID.randomUUID().toString();
        final Object lock = responseManager.createLock(uuid);
        synchronized(lock) {
            kafkaService.send(uuid, KafkaOperation.SAVE, t);
            lock.wait();
        }
        return Mono.just(responseManager.retrieve(uuid));
    }
    public Mono<String> delete(@Validated String id) throws Exception {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataInconsistencyException("Document not found for ID: "+ id)))
                .map(t -> {
                    try {
                        kafkaService.send(UUID.randomUUID().toString(), KafkaOperation.DELETE, t);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return id;
                });
    }
    public Mono<T> findById(String id) {
        return repository.findById(id);
    }
}
