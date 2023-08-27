package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "store")
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompoundIndexes({
    @CompoundIndex(name = "unique_document", def = "{'storeInfo.userInfo.documentNo' : 1}", unique = true),
    @CompoundIndex(name = "unique_email", def = "{'storeInfo.userInfo.email' : 1}", unique = true)
})
public class Store extends DocumentBase {
    @NotNull
    private Float score;
    @NotNull
    private User storeInfo;
}
