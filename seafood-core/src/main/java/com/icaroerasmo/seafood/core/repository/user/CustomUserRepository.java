package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import reactor.core.publisher.Flux;

public interface CustomUserRepository {
    Flux<User> findAllUsersByNamePrefix(String namePrefix);
}
