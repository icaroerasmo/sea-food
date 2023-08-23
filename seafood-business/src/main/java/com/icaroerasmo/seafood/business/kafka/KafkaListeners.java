package com.icaroerasmo.seafood.business.kafka;

import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaListeners {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private KafkaResponseManager responseManager;
    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;
    @KafkaListener(id = "${spring.kafka.producer.group-id}", topics = Constants.KAFKA_OUTPUT_QUEUE)
    public <T> void outputListener(KafkaMessageDTO<T> message) throws Exception {
        log.info("Message {} received", message);
        responseManager.save(message);
    }
}
