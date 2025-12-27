package org.example.aa__backend.domain.auth.controller;

import org.example.aa__backend.domain.auth.payload.TokenDTO;
import org.example.aa__backend.domain.auth.payload.UserLoginDTO;
import org.example.aa__backend.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> logout() {
        // JWT 기반 무상태 구조이므로 서버 측에서 별도 무효화 작업은 하지 않고 클라이언트가 토큰을 폐기하도록 안내
        return ResponseEntity.ok("Logged out");
    }
} 
