package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserSelfUpdateService {
    void updateSelfInfo(UUID userId, String email);
} 
