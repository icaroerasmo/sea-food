package com.icaroerasmo.seafood.business.exceptions;

public class PasswordNotChangedException extends RuntimeException {
    public PasswordNotChangedException(String userId) {
        super("Old and new passwords don't match for user "+userId);
    }
}
