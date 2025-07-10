package org.example.loginjwt__.domain.auth.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
    private String email;
    private String password;
} 