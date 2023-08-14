package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sell")
public class Sell {
    @Id
    private String id;
    @DBRef
    @NonNull
    private Store store;
    @DBRef
    @NonNull
    private User buyer;
    @DBRef
    private List<Item> items;
}
