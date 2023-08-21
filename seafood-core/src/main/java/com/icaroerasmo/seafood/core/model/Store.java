package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "store")
public class Store extends DocumentBase {
    @NotNull
    private Long score;
    @DBRef
    @NotNull
    private LegalEntity storeInfo;
}
