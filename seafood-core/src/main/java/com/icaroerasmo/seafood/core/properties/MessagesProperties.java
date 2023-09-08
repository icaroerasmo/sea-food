package com.icaroerasmo.seafood.core.properties;

import lombok.Data;

@Data
public class MessagesProperties {
    private String groupId = "seafood";
    private Integer backoffDelay = 3000; // 3 seconds in milliseconds
    private Integer numberOfRetries = 5;
    private Integer timeout = 120000; // 2 minutes in milliseconds
}
