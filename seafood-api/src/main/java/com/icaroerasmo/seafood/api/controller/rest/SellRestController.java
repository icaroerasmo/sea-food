package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.api.service.Service;
import com.icaroerasmo.seafood.core.model.Sell;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/rest/sell")
@org.springframework.web.bind.annotation.RestController
public class SellRestController extends RestController<Sell> {
}
