package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.api.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GraphQlController<T> {
    @Autowired
    protected Service<T> service;
}
