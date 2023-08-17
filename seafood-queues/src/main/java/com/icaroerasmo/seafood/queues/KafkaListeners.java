package com.icaroerasmo.seafood.queues;

import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(id = Constants.KAFKA_INPUT_QUEUE, topics = Constants.KAFKA_INPUT_QUEUE)
    public <T> void consume(KafkaMessageDTO<T> payload) {
        // Processar valor do registro
    }
}
