package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    @NotNull
    private AddressType addressType;
    @NotEmpty
    private String address;
    @NotEmpty
    private String number;
    private String complement;
}
