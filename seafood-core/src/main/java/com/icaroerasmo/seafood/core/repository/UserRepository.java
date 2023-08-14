package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.core.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
