package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompoundIndexes({
    @CompoundIndex(name = "unique_document", def = "{'userInfo.documentNo' : 1}", unique = true),
    @CompoundIndex(name = "unique_email", def = "{'userInfo.email' : 1}", unique = true)
})
public class User extends DocumentBase {
    @NotEmpty
    private String password;
    @NotNull
    private Person userInfo;
}
