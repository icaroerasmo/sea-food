package com.icaroerasmo.seafood.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icaroerasmo.seafood.core.dto.ErrorDTO;
import com.icaroerasmo.seafood.core.dto.KafkaMessageDTO;
import com.icaroerasmo.seafood.core.enums.KafkaOperation;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.io.InputStream;

public class KafkaDeserializer <T> implements Deserializer<KafkaMessageDTO<T>> {

    @Override
    public KafkaMessageDTO<T> deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new InstantDeserializerModule());
        JsonNode jsonMap = null;

        try {
            jsonMap = mapper.readTree(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String uuid = jsonMap.get("uuid").asText();
        String clazz = jsonMap.get("clazz").asText();
        Exception exception = null;
        KafkaOperation operation = null;
        T payload = null;
        try {
            operation = mapper.treeToValue(jsonMap.get("operation"), KafkaOperation.class);
            payload = (T) mapper.treeToValue(jsonMap.get("payload"), Class.forName(clazz));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        final JsonNode error = jsonMap.get("error");

        if(!error.isEmpty()) {
            final String exceptionClassName = error.get("clazz").asText();
            try {
                exception = (Exception) mapper.treeToValue(error.get("exception"), Class.forName(exceptionClassName));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return new KafkaMessageDTO<T>(uuid, payload, operation, new ErrorDTO(exception));
    }
}
