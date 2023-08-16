package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.SellService;
import com.icaroerasmo.seafood.core.model.Sell;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SellGraphQlController extends GraphQlController<Sell> {

    @QueryMapping
    public Mono<Sell> findSellById(@Argument String id) {
        return ((SellService) service).findById(id);
    }

    @QueryMapping
    public Flux<Sell> findAllSellsByUserId(@Argument String id) {
        return ((SellService) service).findAllSellsByUserId(id);
    }

    @QueryMapping
    public Flux<Sell> findAllSellsByStoreId(@Argument String id) {
        return ((SellService) service).findAllSellsByStoreId(id);
    }
}
