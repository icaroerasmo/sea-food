package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class RestController<T> {

    @Autowired
    protected Service<T> service;

    @PostMapping
    public Mono<T> save(@RequestBody T t) throws Exception {
        return service.save(t);
    }
    @DeleteMapping
    public Mono<Void> delete(@RequestBody T t) {
        return service.delete(t);
    }
    @GetMapping("/{id}")
    public Mono<T> findById(@PathVariable String id) {
        return service.findById(id);
    }
}
