package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.AddressType;
import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
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
    @NonNull
    protected List<Address> addresses;
}
