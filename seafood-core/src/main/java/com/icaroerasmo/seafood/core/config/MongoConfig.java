package com.icaroerasmo.seafood.core.config;

import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
@DependsOn("reactiveMongoTemplate")
public class MongoConfig {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(User.class).
                ensureIndex(new Index().
                        on("userInfo.documentNo",
                                Sort.Direction.ASC).unique()).subscribe();
        mongoTemplate.indexOps(User.class).
                ensureIndex(new Index().
                        on("userInfo.email",
                                Sort.Direction.ASC).unique()).subscribe();
        mongoTemplate.indexOps(Store.class).
                ensureIndex(new Index().
                        on("storeInfo.userInfo.documentNo",
                                Sort.Direction.ASC).unique()).subscribe();
        mongoTemplate.indexOps(Store.class).
                ensureIndex(new Index().
                        on("storeInfo.userInfo.email",
                                Sort.Direction.ASC).unique()).subscribe();
    }
}
