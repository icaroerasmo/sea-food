package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.core.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/rest/store")
@org.springframework.web.bind.annotation.RestController
public class StoreRestController extends RestController<Store> {
}
