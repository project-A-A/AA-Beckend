package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

public interface AdminUserPasswordUpdateService {
    void updateUserPassword(Long userId, String newPassword);
} 
