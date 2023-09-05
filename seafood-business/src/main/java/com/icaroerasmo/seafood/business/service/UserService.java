package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.dto.PasswordChangeDTO;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.business.exceptions.PasswordNotChangedException;
import com.icaroerasmo.seafood.core.model.Person;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    @Override
    @Cacheable(value = "userById", key = "#id")
    public Mono<User> findById(String id) {
        return super.findById(id);
    }
    @Cacheable(value="userByDocumentNo", key="#documentNo")
    public Mono<User> findUserByDocumentNo(String documentNo) {
        return ((UserRepository) repository).findUserByDocumentNo(documentNo).cache();
    }
    @Cacheable(value="userByEmail", key="#email")
    public Mono<User> findUserByEmail(String email) {
        return ((UserRepository) repository).findUserByEmail(email).cache();
    }
    @Cacheable(value="usersByNamePrefix", key="#namePrefix")
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {
        return ((UserRepository) repository).findAllUsersByNamePrefix(namePrefix).cache();
    }
    @Override
    public Mono<User> save(User user) throws Exception {

        if(user.getId() == null) {
            return super.save(user);
        }

        return repository.
                findById(user.getId()).
                flatMap((savedUser) -> {
                            if(savedUser != null) {
                                Person person = savedUser.getUserInfo();
                                user.setPassword(savedUser.getPassword());
                                user.getUserInfo().setDocumentNo(person.getDocumentNo());
                                user.getUserInfo().setPersonType(person.getPersonType());
                            }
                            try {
                                return super.save(user);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

    }
    public Mono<User> changePassword(PasswordChangeDTO passwordChangeDTO) {
        //TODO encrypt old and new passwords
        return repository.findById(passwordChangeDTO.getUserId()).
                switchIfEmpty(
                        Mono.error(new DataNotFoundException("User not found for ID: "+ passwordChangeDTO.getUserId()))
                ).
                flatMap(user -> {
                   if(user.getPassword().equals(passwordChangeDTO.getOldPasswd())) {
                       user.setPassword(passwordChangeDTO.getNewPasswd());
                       try {
                           return super.save(user);
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   }
                   throw new PasswordNotChangedException(passwordChangeDTO.getUserId());
                }).
                doOnError((exception) -> Mono.error(exception));
    }
}
