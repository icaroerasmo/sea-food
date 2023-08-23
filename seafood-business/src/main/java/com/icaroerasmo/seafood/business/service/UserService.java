package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    public Mono<User> findUserByDocumentNo(String documentNo) {
        return ((UserRepository) repository).findUserByDocumentNo(documentNo);
    }
    public Mono<User> findUserByEmail(String email) {
        return ((UserRepository) repository).findUserByEmail(email);
    }
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {
        return ((UserRepository) repository).findAllUsersByNamePrefix(namePrefix);
    }
}
