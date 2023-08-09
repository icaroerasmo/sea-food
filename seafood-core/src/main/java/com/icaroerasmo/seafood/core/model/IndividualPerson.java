package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter @Setter
@EqualsAndHashCode
public class IndividualPerson extends Person {
    public IndividualPerson() {
        personType = PersonType.LEGAL_ENTITY;
    }
}
