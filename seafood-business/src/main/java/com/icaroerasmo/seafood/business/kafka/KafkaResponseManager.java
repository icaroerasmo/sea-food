package com.icaroerasmo.seafood.business.kafka;

import com.icaroerasmo.seafood.business.exceptions.KafkaMessagesException;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KafkaResponseManager {
    private Map<String, Object> locks = new HashMap<>();
    private List<KafkaMessageDTO<?>> messages = new ArrayList<>();

    public Object createLock(String uuid) {
        Object lock = new Object();
        locks.put(uuid, lock);
        return lock;
    }

    public void save(KafkaMessageDTO<?> message) {
        final Object lock = locks.get(message.getUuid());
        synchronized(lock) {
            messages.add(message);
            lock.notify();
        }
    }

    public <T> T retrieve(String uuid) throws Exception {
        final Object lock = locks.get(uuid);
        final KafkaMessageDTO<T> message =
                (KafkaMessageDTO<T>) messages.stream().
                filter(m -> m.getUuid().equals(uuid)).
                findFirst().orElseThrow(
                        () -> new KafkaMessagesException("Failed retrieving message from Kafka"));

        synchronized(lock) {
            messages.remove(message);
            locks.remove(uuid);
            lock.notify();
        }

        return message.getPayload();
    }
}
