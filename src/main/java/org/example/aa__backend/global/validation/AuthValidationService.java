package org.example.aa__backend.global.validation;

import org.example.aa__backend.global.exception.AuthenticationFailedException;
import org.example.aa__backend.global.util.constants.AccountError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthValidationService {

    public void validateAuthentication(AuthenticationException e) {
        log.debug(AccountError.TOKEN_GENERATION_ERROR.toString() + ":" + e.getMessage());
        throw new AuthenticationFailedException("Authentication is required for this request.", e);
    }

    public void validateTokenGeneration(Exception e) {
        log.error("Token generation unexpected error: {}", e.getMessage());
        throw new AuthenticationFailedException("Token generation failed.", e);
    }
} 
