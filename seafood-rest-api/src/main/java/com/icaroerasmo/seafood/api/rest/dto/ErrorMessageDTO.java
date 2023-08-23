package com.icaroerasmo.seafood.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private Integer httpStatusCode;
    private String errorDescription;
}
