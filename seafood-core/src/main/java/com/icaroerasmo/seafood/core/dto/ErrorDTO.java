package com.icaroerasmo.seafood.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO<T extends Throwable> {
    private Class<T> clazz;
    private T exception;

    public ErrorDTO(T exception) {
        if(exception != null) {
            this.clazz = (Class<T>) exception.getClass();
        }
        this.exception = exception;
    }
}
