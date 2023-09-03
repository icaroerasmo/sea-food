package com.icaroerasmo.seafood.core.listeners;


import com.icaroerasmo.seafood.core.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserMongoListener extends AbstractMongoEventListener<User> {}
