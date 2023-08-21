package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "item")
public class Item extends DocumentBase {
    private String description;
}
