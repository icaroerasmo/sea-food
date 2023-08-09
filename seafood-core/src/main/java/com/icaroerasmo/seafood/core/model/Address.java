package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.AddressType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Address {
    private AddressType addressType;
    private String address;
    private String number;
    private String complement;
}
