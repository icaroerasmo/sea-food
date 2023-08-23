package com.icaroerasmo.seafood.core.repository.user;

import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<User> findUserByDocumentNo(String documentNo) {
        Query query = new Query(Criteria.where("userInfo.documentNo").is(documentNo));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public Mono<User> findUserByEmail(String email) {
        Query query = new Query(Criteria.where("userInfo.email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {

        if(namePrefix.length() < 3) {
            return Flux.empty();
        }

        return mongoTemplate.find(QueryUtil.queryByPrefix("userInfo.name", namePrefix), User.class);
    }
}
