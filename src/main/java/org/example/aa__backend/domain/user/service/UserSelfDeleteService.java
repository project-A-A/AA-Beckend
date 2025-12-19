package org.example.aa__backend.domain.user.service;

import org.springframework.http.ResponseEntity;

public interface UserSelfDeleteService {
    void deleteSelfAccount(Long userId, String password);
} 
