package com.icaroerasmo.seafood.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class InstantSerializerModule extends SimpleModule {
    public InstantSerializerModule() {
        super("InstantSerializerModule");
        addSerializer(Instant.class, new InstantSerializer());
    }
    static class InstantSerializer extends JsonSerializer<Instant> {
        @Override
        public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(Converter.instantSerializer(instant));
        }
    }
}
