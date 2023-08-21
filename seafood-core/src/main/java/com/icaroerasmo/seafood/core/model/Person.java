package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class Person {
    @NotEmpty
    protected String name;
    @NotEmpty
    @Indexed(unique = true)
    protected String documentNo;
    @NotEmpty
    @Indexed(unique = true)
    protected String email;
    @NotEmpty
    protected String phone;
    @NotNull
    protected PersonType personType;
    @NotNull
    protected List<Address> addresses;
}
