package com.icaroerasmo.seafood.core.enums;

import lombok.Getter;

@Getter
public enum Constants {

    KAFKA_INPUT_QUEUE("input.queue"), KAFKA_OUTPUT_QUEUE("output.queue");

    private String value;

    Constants(String value) {
        this.value = value;
    }
}
