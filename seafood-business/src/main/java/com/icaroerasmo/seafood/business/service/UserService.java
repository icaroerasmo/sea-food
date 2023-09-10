package com.icaroerasmo.seafood.business.service;

import com.icaroerasmo.seafood.business.dto.PasswordChangeDTO;
import com.icaroerasmo.seafood.business.exceptions.DataNotFoundException;
import com.icaroerasmo.seafood.business.exceptions.PasswordNotChangedException;
import com.icaroerasmo.seafood.core.model.Person;
import com.icaroerasmo.seafood.core.model.User;
import com.icaroerasmo.seafood.core.repository.user.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Log4j2
@org.springframework.stereotype.Service
public class UserService extends Service<User> {
    @Override
    @Cacheable(value = "userById", key = "#id")
    public Mono<User> findById(String id) {
        return super.findById(ObjectUtils.requireNonEmpty(id, "Id is empty"));
    }
    @Cacheable(value="userByDocumentNo", key="#documentNo")
    public Mono<User> findUserByDocumentNo(String documentNo) {
        return ((UserRepository) repository).findUserByDocumentNo(ObjectUtils.requireNonEmpty(documentNo)).cache();
    }
    @Cacheable(value="userByEmail", key="#email")
    public Mono<User> findUserByEmail(String email) {
        return ((UserRepository) repository).findUserByEmail(ObjectUtils.requireNonEmpty(email, "Email is empty")).cache();
    }
    @Cacheable(value="usersByNamePrefix", key="#namePrefix")
    public Flux<User> findAllUsersByNamePrefix(String namePrefix) {
        return ((UserRepository) repository).findAllUsersByNamePrefix(ObjectUtils.requireNonEmpty(namePrefix, "Name prefix is empty")).cache();
    }
    @Override
    public Mono<User> save(@Valid User user) throws Exception {

        if(user.getId() == null) {
            ObjectUtils.requireNonEmpty(user.getPassword(), "Password cannot be empty");
            ObjectUtils.requireNonEmpty(user.getUserInfo().getDocumentNo(), "Document no cannot empty");
            Objects.requireNonNull(user.getUserInfo().getPersonType(), "Person type cannot be null");
            return super.save(user);
        }

        return repository.
                findById(user.getId()).
                switchIfEmpty(Mono.error(new DataNotFoundException("User not found for ID: "+user.getId()))).
                flatMap((savedUser) -> {

                            Person person = savedUser.getUserInfo();
                            user.setPassword(savedUser.getPassword());
                            user.getUserInfo().setDocumentNo(person.getDocumentNo());
                            user.getUserInfo().setPersonType(person.getPersonType());

                            try {
                                return super.save(user);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

    }
    public Mono<User> changePassword(@Valid PasswordChangeDTO passwordChangeDTO) {
        //TODO encrypt old and new passwords
        return repository.findById(passwordChangeDTO.getUserId()).
                switchIfEmpty(
                        Mono.error(new DataNotFoundException("User not found for ID: "+passwordChangeDTO.getUserId()))
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
    @Caching(evict = {
        @CacheEvict(value = "userById", allEntries = true),
        @CacheEvict(value = "userByDocumentNo", allEntries = true),
        @CacheEvict(value = "userByEmail", allEntries = true),
        @CacheEvict(value = "usersByNamePrefix", allEntries = true)
    })
    @Scheduled(fixedRateString = "#{@cacheProperties.getTtl()}")
    void cacheCleaning() {
        log.info("Cleaning user cache");
    }
}
