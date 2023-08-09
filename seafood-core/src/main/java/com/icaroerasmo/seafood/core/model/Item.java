package com.icaroerasmo.seafood.core.model;

import com.mongodb.lang.NonNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Item {
    @Id
    private String id;
    @NonNull
    private String description;
}
