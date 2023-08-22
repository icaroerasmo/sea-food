package com.icaroerasmo.seafood.core.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public abstract class Converter {

    public static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ISO_INSTANT;
    public static final String DEFAULT_FORMAT_STR = DEFAULT_FORMAT.toString();

    public static String instantSerializer(Instant instant) {
        return DEFAULT_FORMAT.format(instant);
    }

    public static Instant instantDeserializer(String instant) {
        return Instant.from(DEFAULT_FORMAT.parse(instant));
    }

}
