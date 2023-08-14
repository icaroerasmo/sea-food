package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.AddressType;
import lombok.Data;
import lombok.NonNull;

@Data
public class Address {
    @NonNull
    private AddressType addressType;
    @NonNull
    private String address;
    @NonNull
    private String number;
    private String complement;
}
