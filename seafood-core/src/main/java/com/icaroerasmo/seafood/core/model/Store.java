package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "store")
public class Store {
    @Id
    private String id;
    @DBRef
    private LegalEntity storeInfo;
    private Long score;
}
