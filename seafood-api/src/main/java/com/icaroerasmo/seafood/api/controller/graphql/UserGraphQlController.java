package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.business.service.UserService;
import com.icaroerasmo.seafood.core.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class UserGraphQlController extends GraphQlController<User> {
    @QueryMapping
    public Mono<User> findUserById(@Argument String id) {
        return service.findById(id);
    }
    @QueryMapping
    public Flux<User> findAllUsersByNamePrefix(@Argument String namePrefix) {
        return ((UserService) service).findAllUsersByNamePrefix(namePrefix);
    }
    @MutationMapping
    public Mono<User> saveUser(@Argument User user) throws Exception {
        return service.save(user);
    }
    @MutationMapping
    public Mono<User> deleteUser(@Argument String userId) throws Exception {
        return service.delete(userId);
    }
}
