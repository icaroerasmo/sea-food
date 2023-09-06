package com.icaroerasmo.seafood.core.repository.common;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import reactor.core.publisher.Mono;

public interface DocumentBaseRepository<T> {
    Mono<DocumentBase> getDocumentBaseDataById(String id, Class<T> clazz);
}
