package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AdminUserInfoUpdateService {
    void updateUserInfo(UUID userId, String email);
} 
