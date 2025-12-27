package org.example.aa__backend.domain.coffeechat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.coffeechat.payload.CoffeeChatCreateRequest;
import org.example.aa__backend.domain.coffeechat.payload.CoffeeChatResponse;
import org.example.aa__backend.domain.coffeechat.service.CoffeeChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/coffeeChat")
@CrossOrigin(origins = "http://localhost:3000")
public class CoffeeChatController {

    private final CoffeeChatService coffeeChatService;

    @PostMapping
    public ResponseEntity<CoffeeChatResponse> request(Authentication authentication,
                                                      @PathVariable UUID userId,
                                                      @Valid @RequestBody CoffeeChatCreateRequest request) {
        CoffeeChatResponse response = coffeeChatService.request(authentication.getName(), userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CoffeeChatResponse>> list(Authentication authentication,
                                                         @PathVariable UUID userId) {
        return ResponseEntity.ok(coffeeChatService.list(authentication.getName(), userId));
    }
}


