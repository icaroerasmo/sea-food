package com.icaroerasmo.seafood.core.repository;

import com.icaroerasmo.seafood.core.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
