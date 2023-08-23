package com.icaroerasmo.seafood.api.controller.graphql;

import com.icaroerasmo.seafood.business.service.Service;
import com.icaroerasmo.seafood.core.model.DocumentBase;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GraphQlController<T extends DocumentBase> {
    @Autowired
    protected Service<T> service;
}
