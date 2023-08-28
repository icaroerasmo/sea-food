package com.icaroerasmo.seafood.api.graphql.config;

import com.icaroerasmo.seafood.core.util.Converter;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.Instant;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.
                scalar(ExtendedScalars.GraphQLBigDecimal).
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
