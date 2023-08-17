package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public abstract class Person {
    
    protected String name;
    @Indexed(unique = true)
    protected String documentNo;
    @Indexed(unique = true)
    protected String email;
    protected String phone;
    protected PersonType personType;
    protected List<Address> addresses;
}
