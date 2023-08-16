package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {

        if(namePrefix.length() < 3) {
            return Flux.empty();
        }

        return mongoTemplate.find(QueryUtil.queryByPrefix("userInfo.name", namePrefix), User.class);
    }
}
