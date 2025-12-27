package org.example.aa__backend.domain.auth.service.impl;

import org.example.aa__backend.domain.auth.payload.TokenDTO;
import org.example.aa__backend.domain.auth.payload.UserLoginDTO;
import org.example.aa__backend.domain.auth.service.AuthService;
import org.example.aa__backend.global.validation.AuthValidationService;
import org.example.aa__backend.global.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthValidationService authValidationService;

    @Override
    public TokenDTO authenticate(UserLoginDTO userLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
            );
            String token = tokenService.generateToken(authentication);
            return new TokenDTO(token);
        } catch (AuthenticationException e) {
            authValidationService.validateAuthentication(e);
            return null; // Authentication failed: validation handled above
        } catch (Exception e) {
            authValidationService.validateTokenGeneration(e);
            return null; // Token generation failed: validation handled above
        }
    }
} 
