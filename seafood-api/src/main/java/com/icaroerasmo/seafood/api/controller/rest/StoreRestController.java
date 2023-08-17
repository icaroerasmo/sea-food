package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.api.service.StoreService;
import com.icaroerasmo.seafood.core.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RequestMapping("/api/rest/store")
@org.springframework.web.bind.annotation.RestController
public class StoreRestController extends RestController<Store> {

    @GetMapping("/byNamePrefix/{namePrefix}")
    public Flux<Store> findAllStoresByNamePrefix(@PathVariable String namePrefix) {
        return ((StoreService) service).findAllStoresByNamePrefix(namePrefix);
    }
}
