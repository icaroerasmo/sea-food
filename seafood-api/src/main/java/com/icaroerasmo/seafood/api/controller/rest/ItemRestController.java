package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.business.service.ItemService;
import com.icaroerasmo.seafood.core.model.Item;
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
    @GetMapping("/byStoreId/{storeId}")
    public Flux<Item> findAllItemsByStoreId(@PathVariable String storeId) {
        return ((ItemService) service).findAllItemsByStoreId(storeId);
    }
}
