package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.SellService;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.model.Sell;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Controller
public class SellGraphQlController extends GraphQlController<Sell> {
    @QueryMapping
    public Mono<Sell> findSellById(@Argument String id) {
        return service.findById(id);
    }
    @QueryMapping
    public Flux<Sell> findAllSellsByUserId(@Argument String id) {
        return ((SellService) service).findAllSellsByUserId(id);
    }
    @QueryMapping
    public Flux<Sell> findAllSellsByStoreId(@Argument String id) {
        return ((SellService) service).findAllSellsByStoreId(id);
    }
    @MutationMapping
    public Mono<Sell> saveSell(@Argument Sell sell) throws Exception {
        return service.save(sell);
    }
    @MutationMapping
    public Mono<Sell> deleteSell(@Argument String sellId) throws Exception {
        return service.delete(sellId);
    }
}
