package com.icaroerasmo.seafood.api.rest.controller;

import com.icaroerasmo.seafood.business.service.UserService;
import com.icaroerasmo.seafood.core.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@RequestMapping("/api/rest/user")
@org.springframework.web.bind.annotation.RestController
public class UserRestController extends RestController<User> {

    @GetMapping("/byNamePrefix/{namePrefix}")
    public Flux<User> findAllStoresByNamePrefix(@PathVariable String namePrefix) {
        return ((UserService) service).findAllUsersByNamePrefix(namePrefix);
    }
}
