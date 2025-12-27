package org.example.aa__backend.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.user.payload.AccountDTO;
import org.example.aa__backend.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SignUpController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody AccountDTO accountDTO) {
        String result = userService.createUser(accountDTO);
        return ResponseEntity.ok(result);
    }
}

