package com.icaroerasmo.seafood.business.kafka;

import com.icaroerasmo.seafood.business.service.KafkaService;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@KafkaListener(id = "${spring.kafka.producer.group-id}", topics = Constants.KAFKA_OUTPUT_QUEUE)
public class KafkaListeners {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;
    @KafkaHandler
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "${@MessagesProperties.getNumberOfRetries()}",
            autoCreateTopics = "false")
    public <T> void outputListener(KafkaMessageDTO<T> message) throws Exception {
        log.info("Message {} received", message);
        kafkaService.save(message);
    }
}
