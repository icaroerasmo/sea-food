package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.api.kafka.KafkaResponseManager;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.Constants;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, KafkaMessageDTO<?>> kafkaTemplate;
    @Autowired
    private KafkaResponseManager responseManager;

    public <T> String send(KafkaOperation operation, T t){
        final UUID uuid = UUID.randomUUID();
        responseManager.createLock(uuid.toString());
        kafkaTemplate.send(Constants.KAFKA_INPUT_QUEUE, new KafkaMessageDTO<>(uuid.toString(), t, operation));
        log.info("Message {} sent to consumer", t);
        return uuid.toString();
    }
}
