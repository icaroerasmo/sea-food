package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "sell")
public class Sell extends DocumentBase {
    @DBRef
    private Store store;
    @DBRef
    private User buyer;
    @DBRef
    private List<Item> items;
}
