package org.example.aa__backend.domain.admin.service;

import org.springframework.http.ResponseEntity;

public interface AdminUserRoleUpdateService {
    void updateUserRole(Long userId, String newRole);
} 
