package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.ItemService;
import com.icaroerasmo.seafood.core.model.Item;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ItemController extends GraphQlController<Item> {

    private ItemService _service = (ItemService) service;

    @QueryMapping
    public Mono<Item> findItemById(@Argument String id) {
        return service.findById(id);
    }

    @QueryMapping
    public Flux<Item> findAllItemsByDescriptionPrefix(@Argument String descriptionPrefix) {
        return _service.findAllItemsByDescriptionPrefix(descriptionPrefix);
    }
}
