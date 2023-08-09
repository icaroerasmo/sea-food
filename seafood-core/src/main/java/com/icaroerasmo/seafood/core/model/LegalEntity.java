package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.*;


@ToString
@Getter @Setter
@EqualsAndHashCode
public class LegalEntity extends Person {
    public LegalEntity() {
        personType = PersonType.LEGAL_ENTITY;
    }
}
