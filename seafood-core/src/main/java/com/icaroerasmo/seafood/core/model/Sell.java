package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "sell")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sell extends DocumentBase {
    @NotNull
    private Store store;
    @NotNull
    private User buyer;
    @NotNull
    private List<Item> items;
}
