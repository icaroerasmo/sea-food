package com.icaroerasmo.seafood.api.rest.controller;

import com.icaroerasmo.seafood.business.service.UserService;
import com.icaroerasmo.seafood.core.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/rest/user")
@org.springframework.web.bind.annotation.RestController
public class UserRestController extends RestController<User> {
    @GetMapping("/byDocumentNo/{documentNo}")
    public Mono<User> findUserByDocumentNo(@PathVariable String documentNo) {
        return((UserService) service).findUserByDocumentNo(documentNo);
    }
    @GetMapping("/byEmail/{email}")
    public Mono<User> findUserByEmail(@PathVariable String email) {
        return((UserService) service).findUserByEmail(email);
    }
    @GetMapping("/byNamePrefix/{namePrefix}")
    public Flux<User> findAllStoresByNamePrefix(@PathVariable String namePrefix) {
        return ((UserService) service).findAllUsersByNamePrefix(namePrefix);
    }
}
