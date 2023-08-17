package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class RestController<T> {
    @Autowired
    protected Service<T> service;

    @PostMapping
    public Mono<T> save(T t) {
        return service.save(t);
    }
    @DeleteMapping
    public Mono<Void> delete(T t) {
        return service.delete(t);
    }
    @GetMapping("/{id}")
    public Mono<T> findById(@PathVariable String id) {
        return service.findById(id);
    }
}
