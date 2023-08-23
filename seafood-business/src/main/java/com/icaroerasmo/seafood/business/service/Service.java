package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.business.kafka.KafkaResponseManager;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Log4j2
public abstract class Service<T extends DocumentBase> {
    @Autowired
    protected ReactiveMongoRepository<T, String> repository;
    @Autowired
    private KafkaResponseManager responseManager;
    @Autowired
    private KafkaService kafkaService;
    public Mono<T> save(T t) throws Exception {
        final String uuid = UUID.randomUUID().toString();
        final Object lock = responseManager.createLock(uuid);
        synchronized(lock) {
            kafkaService.send(uuid, KafkaOperation.SAVE, t);
            lock.wait();
        }
        return Mono.just(responseManager.retrieve(uuid));
    }
    public Mono<T> delete(String id) throws Exception {
        final String uuid = UUID.randomUUID().toString();
        final Object lock = responseManager.createLock(uuid);
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new DataNotFoundException("Document not found for ID: "+ id)))
                .doOnSuccess((t) -> {
                    synchronized(lock) {
                        try {
                            kafkaService.send(UUID.randomUUID().toString(), KafkaOperation.DELETE, t);
                            lock.wait();

                            responseManager.retrieve(uuid);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
    public Mono<T> findById(String id) {
        return repository.findById(id).switchIfEmpty(
                Mono.error(new DataNotFoundException(
                        "Document not found for ID: "+ id)));
    }
}
