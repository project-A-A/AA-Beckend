package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AdminUserRoleUpdateService {
    void updateUserRole(UUID userId, String newRole);
} 
