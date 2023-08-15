package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.core.model.Store;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class StoreController {

    @QueryMapping
    public Mono<Store> findStoreById(@Argument String id) {
        return null;
    }

    @QueryMapping
    public Flux<Store> findAllStoresByNamePrefix(@Argument String namePrefix) {
        return null;
    }
}
