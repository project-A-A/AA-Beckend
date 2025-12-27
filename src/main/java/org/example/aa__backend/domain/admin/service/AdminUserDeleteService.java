package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AdminUserDeleteService {
    void deleteUser(UUID userId);
} 
