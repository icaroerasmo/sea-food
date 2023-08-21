package com.icaroerasmo.seafood.core.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "store")
public class Store extends DocumentBase {
    private Long score;
    @DBRef
    private LegalEntity storeInfo;
}
