package com.icaroerasmo.seafood.core.dto;

import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KafkaMessageDTO<T> {
    private String uuid;
    private T payload;
    private Class<T> clazz;
    private KafkaOperation operation;
    public KafkaMessageDTO(String uuid, T payload, KafkaOperation operation) {
        this.uuid = uuid;
        this.payload = payload;
        this.clazz = (Class<T>) payload.getClass();
        this.operation = operation;
    }
}
