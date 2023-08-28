package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "store")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Store extends DocumentBase {
    @NotNull
    private Float score;
    @NotNull
    private User storeInfo;
}
