package com.icaroerasmo.seafood.queues.services;

import com.icaroerasmo.seafood.core.model.DocumentBase;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    @Override
    public Mono<User> save(User user) {
        Mono<DocumentBase> docBaseMono =
                ((UserRepository) repository).
                        getDocumentBaseDataById(user.getId(), User.class);
        return super.save(user, docBaseMono);
    }
}
