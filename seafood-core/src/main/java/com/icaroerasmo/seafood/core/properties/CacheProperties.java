package com.icaroerasmo.seafood.core.properties;

import lombok.Data;

@Data
public class CacheProperties {
    private Integer ttl = 180000; // 3 minutes in milliseconds
}
