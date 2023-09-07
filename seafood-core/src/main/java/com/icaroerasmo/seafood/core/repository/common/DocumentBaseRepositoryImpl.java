package com.icaroerasmo.seafood.core.repository.common;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public class DocumentBaseRepositoryImpl<T> implements DocumentBaseRepository<T> {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    @Override
    public Mono<DocumentBase> getDocumentBaseDataById(String id, Class<T> clazz) {
        final Query query = new Query(Criteria.where("id").is(id));
        query.fields().
                include("id").
                include("createdAt").
                include("updatedAt");
        return (Mono<DocumentBase>) mongoTemplate.findOne(query, clazz);
    }
}
