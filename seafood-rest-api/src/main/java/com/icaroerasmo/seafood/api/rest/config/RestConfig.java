package com.icaroerasmo.seafood.api.rest.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Configuration
public class RestConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addCustomTimeSerialization() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.serializerByType(Instant.class, new JsonSerializer<Instant>() {

                    private final DateTimeFormatter formatter =
                            new DateTimeFormatterBuilder().appendInstant().toFormatter();

                    @Override
                    public void serialize(
                            Instant instant, JsonGenerator generator, SerializerProvider provider) throws IOException {
                        generator.writeString(formatter.format(instant));
                    }
                });
    }
}
