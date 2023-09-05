package com.icaroerasmo.seafood.business.properties;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Data
@Configuration
@ConfigurationProperties(prefix = "seafood")
public class SeafoodProperties {
    private CacheProperties cache = new CacheProperties();
    @Bean
    @ConfigurationProperties(prefix = "cache")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected CacheProperties cacheProperties() {
        return cache;
    }

    @Data
    public class CacheProperties {
        private Integer ttl = 180000; // 3 minutes in milliseconds
    }
}
