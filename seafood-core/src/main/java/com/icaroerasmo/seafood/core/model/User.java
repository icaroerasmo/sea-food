package com.icaroerasmo.seafood.core.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User extends DocumentBase {
    @NotEmpty
    private String password;
    @NotNull
    private Person userInfo;
}
