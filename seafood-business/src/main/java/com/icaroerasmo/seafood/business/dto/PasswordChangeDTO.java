package com.icaroerasmo.seafood.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String oldPasswd;
    @NotEmpty
    private String newPasswd;
}
