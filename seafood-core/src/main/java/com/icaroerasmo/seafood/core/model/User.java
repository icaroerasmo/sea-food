package com.icaroerasmo.seafood.core.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User extends DocumentBase {
    private String password;
    private Person userInfo;
}
