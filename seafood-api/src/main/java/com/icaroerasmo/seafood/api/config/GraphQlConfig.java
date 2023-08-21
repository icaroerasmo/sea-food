package com.icaroerasmo.seafood.api.config;

import com.icaroerasmo.seafood.core.enums.PersonType;
import com.icaroerasmo.seafood.core.model.Person;
import graphql.TypeResolutionEnvironment;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import graphql.schema.idl.EnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.
                scalar(ExtendedScalars.GraphQLLong).
                scalar(localDateTimeScalarType()).
                type(personTypeWiring());
    }

    public TypeRuntimeWiring personTypeWiring() {
        return TypeRuntimeWiring.newTypeWiring("Person",
                typeBuilder -> typeBuilder.typeResolver(
                        new TypeResolver() {
                            @Override
                            public GraphQLObjectType getType(TypeResolutionEnvironment env) {
                                final String personType = (String) env.getArguments().get("personType");
                                return (GraphQLObjectType) env.getSchema().getType(personType);
                            }
                        }
                    ));
    }

    public GraphQLScalarType localDateTimeScalarType() {
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("LocalDateTime scalar")
                .coercing(new Coercing() {
                    @Override
                    public String serialize(Object input) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.ENGLISH);
                        return formatter.format((LocalDateTime)input);
                    }

                    @Override
                    public Object parseValue(Object o) throws CoercingParseValueException {
                        return o;
                    }

                    @Override
                    public Object parseLiteral(Object o) throws CoercingParseLiteralException {
                        return o.toString();
                    }
                })
                .build();
    }
}