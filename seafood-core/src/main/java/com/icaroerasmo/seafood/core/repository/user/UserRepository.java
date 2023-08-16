package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository {
}