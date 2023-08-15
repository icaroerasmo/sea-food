package com.icaroerasmo.seafood.config;

import com.icaroerasmo.seafood.core.enums.PersonType;
import com.icaroerasmo.seafood.core.model.Person;
import graphql.TypeResolutionEnvironment;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;
import graphql.schema.idl.EnumValuesProvider;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.TypeRuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.
                scalar(ExtendedScalars.DateTime).
                scalar(ExtendedScalars.GraphQLLong).
                type(TypeRuntimeWiring.newTypeWiring("Person", typeBuilder -> typeBuilder.typeResolver(new TypeResolver() {
                    @Override
                    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
                        final String personType = (String) env.getArguments().get("personType");
                        return (GraphQLObjectType) env.getSchema().getType(personType);
                    }
                })));
    }
}