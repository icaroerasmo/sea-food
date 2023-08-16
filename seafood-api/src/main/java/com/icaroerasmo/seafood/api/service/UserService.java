package com.icaroerasmo.seafood.api.service;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import reactor.core.publisher.Flux;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    private UserRepository _repository = (UserRepository) repository;

    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {
        return _repository.findAllUsersByNamePrefix(namePrefix);
    }
}
