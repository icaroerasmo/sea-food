package com.icaroerasmo.seafood.core.enums;

import lombok.Getter;

@Getter
public abstract class Constants {
    public static final String KAFKA_INPUT_QUEUE = "input.queue";
    public static final String KAFKA_OUTPUT_QUEUE = "output.queue";
    public static final String KAFKA_DLT_SUFFIX = "-dlt";
    public static final String KAFKA_DLT_SUFFIX_ID = KAFKA_DLT_SUFFIX+"-2";
}
