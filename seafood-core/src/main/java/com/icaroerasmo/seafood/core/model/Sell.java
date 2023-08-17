package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "sell")
public class Sell {
    @Id
    private String id;
    @DBRef
    
    private Store store;
    @DBRef
    
    private User buyer;
    @DBRef
    private List<Item> items;
    @CreatedDate
    private Instant createdAt;
}
