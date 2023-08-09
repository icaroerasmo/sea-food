package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
public class IndividualPerson extends Person {
    public IndividualPerson() {
        personType = PersonType.LEGAL_ENTITY;
    }
}
