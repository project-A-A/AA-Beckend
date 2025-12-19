package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

public interface UserSelfUpdateService {
    void updateSelfInfo(Long userId, String email);
} 
