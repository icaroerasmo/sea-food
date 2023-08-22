package com.icaroerasmo.seafood.core.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantDeserializerModule extends SimpleModule {
    public InstantDeserializerModule() {
        super("InstantDeserializerModule");
        addDeserializer(Instant.class, new InstantDeserializer());
    }

    static class InstantDeserializer extends JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return Converter.instantDeserializer(jsonParser.getText());
        }
    }

}
