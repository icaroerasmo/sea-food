package com.icaroerasmo.seafood.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private Integer httpStatusCode;
    private String errorDescription;
}
