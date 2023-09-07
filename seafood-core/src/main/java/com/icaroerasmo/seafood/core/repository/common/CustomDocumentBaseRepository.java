package com.icaroerasmo.seafood.core.repository.common;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import reactor.core.publisher.Mono;

public interface CustomDocumentBaseRepository<T> {
    Mono<DocumentBase> getDocumentBaseDataById(T t);
}
