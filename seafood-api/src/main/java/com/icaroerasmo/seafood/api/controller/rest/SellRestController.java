package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.SellService;
import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.core.model.Sell;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/rest/sell")
@org.springframework.web.bind.annotation.RestController
public class SellRestController extends RestController<Sell> {
    @GetMapping("/byUserId/{userId}")
    public Flux<Sell> findAllSellsByUserId(@PathVariable String userId) {
        return ((SellService) service).findAllSellsByUserId(userId);
    }
    @GetMapping("/byStoreId/{storeId}")
    public Flux<Sell> findAllSellsByStoreId(@PathVariable String storeId) {
        return ((SellService) service).findAllSellsByStoreId(storeId);
    }
}
