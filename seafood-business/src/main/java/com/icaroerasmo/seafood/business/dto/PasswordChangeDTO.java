package com.icaroerasmo.seafood.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {
    private String userId;
    private String oldPasswd;
    private String newPasswd;
}
