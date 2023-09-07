package com.icaroerasmo.seafood.core.properties;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Data
@Log4j2
@Configuration
@ConfigurationProperties(prefix = "seafood")
public class SeafoodProperties {
    private final CacheProperties cache = new CacheProperties();
    private final MessagesProperties messages = new MessagesProperties();
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected CacheProperties cacheProperties() {
        return cache;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected MessagesProperties messagesProperties() {return messages;};
}
