package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.api.service.StoreService;
import com.icaroerasmo.seafood.api.service.UserService;
import com.icaroerasmo.seafood.core.model.Store;
import com.icaroerasmo.seafood.core.model.User;
import lombok.RequiredArgsConstructor;
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
