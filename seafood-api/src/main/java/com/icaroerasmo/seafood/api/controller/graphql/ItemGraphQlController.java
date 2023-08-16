package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.ItemService;
import com.icaroerasmo.seafood.core.model.Item;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ItemGraphQlController extends GraphQlController<Item> {

    @QueryMapping
    public Mono<Item> findItemById(@Argument String id) {
        return service.findById(id);
    }

    @QueryMapping
    public Flux<Item> findAllItemsByDescriptionPrefix(@Argument String descriptionPrefix) {
        return ((ItemService) service).findAllItemsByDescriptionPrefix(descriptionPrefix);
    }
}