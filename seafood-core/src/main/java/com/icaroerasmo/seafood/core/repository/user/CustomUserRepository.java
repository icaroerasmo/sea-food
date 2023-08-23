package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomUserRepository {
    Mono<User> findUserByDocumentNo(String documentNo);
    Mono<User> findUserByEmail(String email);
    Flux<User> findAllUsersByNamePrefix(String namePrefix);
}
