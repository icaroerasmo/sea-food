package com.icaroerasmo.seafood.core.model;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "item")
public class Item {
    @Id
    private String id;
    @NonNull
    private String description;
}
