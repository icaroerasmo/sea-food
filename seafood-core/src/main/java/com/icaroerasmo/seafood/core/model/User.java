package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @NonNull
    private String password;
    @NonNull
    private Person userInfo;
}
