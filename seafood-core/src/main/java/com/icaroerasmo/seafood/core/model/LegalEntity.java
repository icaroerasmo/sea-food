package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;

import java.util.List;

@ToString
@Getter @Setter
@EqualsAndHashCode
public class LegalEntity extends Person {
    public LegalEntity(String name,
                            String documentNo,
                            String email,
                            String phone,
                            List<Address> addresses) {
        super(name, documentNo, email, phone, PersonType.LEGAL_ENTITY, addresses);
    }
}
