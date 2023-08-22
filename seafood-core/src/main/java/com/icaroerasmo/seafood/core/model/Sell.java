package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "sell")
public class Sell extends DocumentBase {
    @DBRef
    @NotNull
    private Store store;
    @DBRef
    @NotNull
    private User buyer;
    @DBRef
    @NotNull
    private List<Item> items;
}
