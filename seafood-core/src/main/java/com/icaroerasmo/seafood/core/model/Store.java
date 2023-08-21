package com.icaroerasmo.seafood.core.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "store")
public class Store extends DocumentBase {
    @DBRef
    private LegalEntity storeInfo;
    private Long score;
}
