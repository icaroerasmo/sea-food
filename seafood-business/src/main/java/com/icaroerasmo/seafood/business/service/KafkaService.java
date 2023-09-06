package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.exceptions.KafkaMessagesException;
import com.icaroerasmo.seafood.business.properties.MessagesProperties;
import com.icaroerasmo.seafood.core.dto.ErrorDTO;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;


@Log4j2
@Service
public class KafkaService {
    @Autowired
    private MessagesProperties messagesProperties;
    private Map<String, Object> locks = new HashMap<>();
    private List<KafkaMessageDTO<?>> messages = new ArrayList<>();
    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;
    public <T> T send(KafkaOperation operation, T t) throws Exception {
        final String uuid = UUID.randomUUID().toString();
        final Object lock = createLock(uuid);
        synchronized(lock) {
            kafkaTemplate.send(Constants.KAFKA_INPUT_QUEUE, new KafkaMessageDTO<>(uuid, t, operation));
            log.info("Message {} sent to consumer", t);
            lock.wait(messagesProperties.getTimeout());
        }
        return retrieve(uuid);
    }
    public void save(KafkaMessageDTO<?> message) {
        final Object lock = locks.get(message.getUuid());
        if(lock == null) {
            return;
        }
        synchronized(lock) {
            messages.add(message);
            lock.notify();
        }
    }
    private Object createLock(String uuid) {
        Object lock = new Object();
        locks.put(uuid, lock);
        return lock;
    }
    private <T> T retrieve(String uuid) throws Exception {
        Object lock = null;
        KafkaMessageDTO<T> message = null;
        try {
            lock = locks.get(uuid);
            message =
                    (KafkaMessageDTO<T>) messages.stream().
                            filter(m -> m.getUuid().equals(uuid)).
                            findFirst().orElseThrow(
                                    () -> new KafkaMessagesException("Failed retrieving message from Kafka"));

            final ErrorDTO error = message.getError();

            if (error != null) {
                throw (Exception) error.getException();
            }

            return message.getPayload();
        } catch (Exception e) {
            log.info("Error trying to retrieve data from Kafka");
            throw e;
        } finally {
            if(lock != null) {
                synchronized (lock) {
                    if (message != null) {
                        messages.remove(message);
                    }
                    locks.remove(uuid);
                    lock.notify();
                }
            }
        }
    }
}
