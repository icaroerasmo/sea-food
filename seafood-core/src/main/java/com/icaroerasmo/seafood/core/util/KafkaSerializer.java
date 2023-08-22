package com.icaroerasmo.seafood.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class KafkaSerializer <T> implements Serializer<KafkaMessageDTO<T>> {
    @Override
    public byte[] serialize(String s, KafkaMessageDTO<T> tKafkaMessageDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new InstantSerializerModule());
        try {
            return mapper.writeValueAsBytes(tKafkaMessageDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
