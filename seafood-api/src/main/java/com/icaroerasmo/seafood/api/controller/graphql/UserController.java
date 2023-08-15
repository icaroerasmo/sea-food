package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.UserService;
import com.icaroerasmo.seafood.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @QueryMapping
    public Mono<User> findUserById(@Argument String id) {
        return service.findById(id);
    }

    @QueryMapping
    public Flux<User> findAllUsersByNamePrefix(@Argument String namePrefix) {
        return service.findAll();
    }
}
