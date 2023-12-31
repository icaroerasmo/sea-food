package com.icaroerasmo.seafood.api.rest.controller;

import com.icaroerasmo.seafood.business.service.Service;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

public abstract class RestController<T extends DocumentBase> {
    @Autowired
    protected Service<T> service;
    @PostMapping
    public Mono<T> save(@Valid @RequestBody T t) throws Exception {
        return service.save(t);
    }
    @DeleteMapping("/{id}")
    public Mono<T> delete(@PathVariable String id) throws Exception {
        return service.delete(id);
    }
    @GetMapping("/{id}")
    public Mono<T> findById(@PathVariable String id) {
        return service.findById(id);
    }
}
