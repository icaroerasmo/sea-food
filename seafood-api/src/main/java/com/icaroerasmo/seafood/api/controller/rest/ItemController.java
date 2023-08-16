package com.icaroerasmo.seafood.api.controller.rest;

import com.icaroerasmo.seafood.core.model.Item;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/rest/item")
@org.springframework.web.bind.annotation.RestController
public class ItemController extends RestController<Item> {
}
