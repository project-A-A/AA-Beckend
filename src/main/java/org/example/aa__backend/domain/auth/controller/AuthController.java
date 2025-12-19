package org.example.aa__backend.domain.auth.controller;

import org.example.aa__backend.domain.auth.payload.TokenDTO;
import org.example.aa__backend.domain.auth.payload.UserLoginDTO;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.example.aa__backend.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name="Auth Controller", description = "Controller for Authentication")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDTO> token(@RequestBody UserLoginDTO userLogin) {
        TokenDTO tokenDTO = authService.authenticate(userLogin);
        return ResponseEntity.ok(tokenDTO);
    }
} 
