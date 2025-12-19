package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

public interface UserSelfPasswordUpdateService {
    void updateSelfPassword(Long userId, String currentPassword, String newPassword);
} 
