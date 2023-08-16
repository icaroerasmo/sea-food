package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.SellService;
import com.icaroerasmo.seafood.core.model.Sell;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SellController extends GraphQlController<Sell> {

    private SellService _service = (SellService) service;

    @QueryMapping
    public Mono<Sell> findSellById(@Argument String id) {
        return service.findById(id);
    }

    @QueryMapping
    public Flux<Sell> findAllSellsByUserId(@Argument String id) {
        return _service.findAllSellsByUserId(id);
    }

    @QueryMapping
    public Flux<Sell> findAllSellsByStoreId(@Argument String id) {
        return _service.findAllSellsByStoreId(id);
    }
}
