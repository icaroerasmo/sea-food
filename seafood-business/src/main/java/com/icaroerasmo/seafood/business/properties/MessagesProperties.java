package com.icaroerasmo.seafood.business.properties;

import lombok.Data;

@Data
public class MessagesProperties {
    private Integer timeout = 120000; // 2 minutes in milliseconds
}
