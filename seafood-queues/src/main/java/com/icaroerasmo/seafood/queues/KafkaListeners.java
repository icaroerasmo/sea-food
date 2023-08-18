package com.icaroerasmo.seafood.queues;

import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaListeners {
    @KafkaListener(id = "${spring.kafka.producer.group-id}", topics = Constants.KAFKA_INPUT_QUEUE)
    public <T> void consume(KafkaMessageDTO<T> payload) {
        log.info("Message {} received", payload);
    }
}
