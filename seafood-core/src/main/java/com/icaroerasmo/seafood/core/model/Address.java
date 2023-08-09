package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.AddressType;
import lombok.Data;

@Data
public class Address {
    private AddressType addressType;
    private String address;
    private String number;
    private String complement;
}
