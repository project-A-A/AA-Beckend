package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserSelfPasswordUpdateService {
    void updateSelfPassword(UUID userId, String currentPassword, String newPassword);
} 
