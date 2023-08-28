package com.icaroerasmo.seafood.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.icaroerasmo.seafood.core.enums.PersonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @NotEmpty
    protected String name;
    @NotEmpty
    protected String documentNo;
    @NotEmpty
    protected String email;
    @NotEmpty
    protected String phone;
    @NotNull
    protected PersonType personType;
    @NotNull
    protected List<Address> addresses;
}
