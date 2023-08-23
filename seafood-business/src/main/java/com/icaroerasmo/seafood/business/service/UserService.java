package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {
        return ((UserRepository) repository).findAllUsersByNamePrefix(namePrefix);
    }
}
