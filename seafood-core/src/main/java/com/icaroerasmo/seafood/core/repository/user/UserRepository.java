package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.common.CustomDocumentBaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomDocumentBaseRepository<User>, CustomUserRepository {
}
