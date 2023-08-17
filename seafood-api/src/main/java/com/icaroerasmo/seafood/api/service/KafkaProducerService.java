package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.core.enums.KafkaMessageType;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import com.icaroerasmo.seafood.core.enums.KafkaTopic;
import com.icaroerasmo.seafood.core.model.Item;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public <T> void send(KafkaOperation operation, T t){
        final KafkaTopic topic = KafkaTopic.findTopic(operation, KafkaMessageType.REQUEST, t);
        kafkaTemplate.send(topic.name(), t);
    }
}
