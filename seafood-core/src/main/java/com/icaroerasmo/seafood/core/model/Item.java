package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Data
@Document(collection = "item")
public class Item extends DocumentBase {
    @DBRef
    @NotNull
    private Store store;
    @NotEmpty
    private String description;
}
