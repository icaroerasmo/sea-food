package com.icaroerasmo.seafood.api.graphql.config;

import com.icaroerasmo.seafood.core.util.Converter;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.
                scalar(ExtendedScalars.GraphQLLong).
                scalar(localDateTimeScalarType());
    }

    public GraphQLScalarType localDateTimeScalarType() {
        return GraphQLScalarType.newScalar()
                .name("Instant")
                .description("Instant scalar")
                .coercing(new Coercing<Instant, String>() {
                    @Override
                    public String serialize(Object input) {
                        return Converter.instantSerializer((Instant) input);
                    }
                })
                .build();
    }
}