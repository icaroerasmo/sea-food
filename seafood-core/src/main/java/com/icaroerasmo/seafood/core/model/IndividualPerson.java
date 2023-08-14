package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@ToString
@Getter @Setter
@EqualsAndHashCode
public class IndividualPerson extends Person {
    public IndividualPerson(String name,
                            String documentNo,
                            String email,
                            String phone,
                            List<Address> addresses) {
        super(name, documentNo, email, phone, PersonType.INDIVIDUAL_PERSON, addresses);
    }
}
