package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.ItemService;
import com.icaroerasmo.seafood.core.model.Item;
import com.icaroerasmo.seafood.core.util.QueryUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RequestMapping("/api/rest/item")
@org.springframework.web.bind.annotation.RestController
public class ItemRestController extends RestController<Item> {

    @GetMapping("/byDescriptionPrefix/{descriptionPrefix}")
    public Flux<Item> findAllItemsByDescriptionPrefix(@PathVariable String descriptionPrefix) {
        return ((ItemService) service).findAllItemsByDescriptionPrefix(descriptionPrefix);
    }
}
