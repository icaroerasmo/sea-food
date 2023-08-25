package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "item")
public class Item extends DocumentBase {
    @NotNull
    private Store store;
    @NotEmpty
    private String description;
}
