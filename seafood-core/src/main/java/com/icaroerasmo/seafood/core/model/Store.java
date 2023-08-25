package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "store")
public class Store extends DocumentBase {
    @NotNull
    private Float score;
    @NotNull
    private User storeInfo;
}
