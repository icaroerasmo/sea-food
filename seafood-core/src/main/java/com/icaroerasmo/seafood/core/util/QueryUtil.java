package com.icaroerasmo.seafood.core.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class QueryUtil {
    public static Query queryByPrefix(String fieldName, String namePrefix) {
        return new Query(Criteria.where(fieldName).regex(namePrefix+".*"));
    }
}
