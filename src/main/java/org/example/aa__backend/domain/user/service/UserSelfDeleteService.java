package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserSelfDeleteService {
    void deleteSelfAccount(UUID userId, String password);
} 
