package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

public interface AdminUserInfoUpdateService {
    void updateUserInfo(Long userId, String email);
} 
