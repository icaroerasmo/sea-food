package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Getter @Setter
public abstract class Person {
    @NonNull
    protected String name;
    @NonNull
    @Indexed(unique = true)
    protected String documentNo;
    @NonNull
    @Indexed(unique = true)
    protected String email;
    @NonNull
    protected String phone;
    @NonNull
    protected PersonType personType;
    protected List<Address> addresses;
}
