package com.icaroerasmo.seafood.api.graphql.controller;

import com.icaroerasmo.seafood.business.dto.PasswordChangeDTO;
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
    public Mono<User> findUserByDocumentNo(@Argument String documentNo) {
        return ((UserService) service).findUserByDocumentNo(documentNo);
    }
    @QueryMapping
    public Mono<User> findUserByEmail(@Argument String email) {
        return ((UserService) service).findUserByEmail(email);
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
    public Mono<User> changePasswd(@Argument PasswordChangeDTO passwordChange) {
        return ((UserService) service).changePassword(passwordChange);
    }
    @MutationMapping
    public Mono<User> deleteUser(@Argument String userId) throws Exception {
        return service.delete(userId);
    }
}
