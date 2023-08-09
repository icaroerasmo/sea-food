package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@ToString
@Getter @Setter
@EqualsAndHashCode
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
