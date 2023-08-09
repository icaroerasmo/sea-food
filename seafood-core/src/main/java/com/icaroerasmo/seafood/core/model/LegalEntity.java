package com.icaroerasmo.seafood.core.model;

import com.icaroerasmo.seafood.core.enums.PersonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LegalEntity extends Person {
    public LegalEntity() {
        personType = PersonType.LEGAL_ENTITY;
    }
}
