package com.icaroerasmo.seafood.core.dto;

import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KafkaMessageDTO<T> {
    private String uuid;
    private T payload;
    private Class<T> clazz;
    private KafkaOperation operation;
    private ErrorDTO error;
    public KafkaMessageDTO(String uuid, T payload, KafkaOperation operation) {
        this.uuid = uuid;
        this.payload = payload;
        this.clazz = (Class<T>) payload.getClass();
        this.operation = operation;
    }
}
