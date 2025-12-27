package org.example.aa__backend.domain.admin.controller;

import org.example.aa__backend.domain.user.service.UserQueryService;
import org.example.aa__backend.domain.admin.service.AdminUserQueryService;
import org.example.aa__backend.domain.admin.service.AdminUserDeleteService;
import org.example.aa__backend.domain.admin.service.AdminUserRoleUpdateService;
import org.example.aa__backend.domain.admin.service.AdminUserInfoUpdateService;
import org.example.aa__backend.domain.admin.service.AdminUserPasswordUpdateService;
import org.example.aa__backend.domain.admin.service.AdminSystemInfoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name="Admin Controller", description = "Controller for Admin management")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final UserQueryService userQueryService;
    private final AdminUserQueryService adminUserQueryService;
    private final AdminUserDeleteService adminUserDeleteService;
    private final AdminUserRoleUpdateService adminUserRoleUpdateService;
    private final AdminUserInfoUpdateService adminUserInfoUpdateService;
    private final AdminUserPasswordUpdateService adminUserPasswordUpdateService;
    private final AdminSystemInfoService adminSystemInfoService;

    @GetMapping("/users")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getAllUsers() {
        return adminUserQueryService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getUserById(@PathVariable UUID userId) {
        return userQueryService.getUserById(userId);
    }

    @GetMapping("/statistics")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getUserStatistics() {
        return adminUserQueryService.getUserStatistics();
    }

    @GetMapping("/system")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getSystemInfo() {
        return adminSystemInfoService.getSystemInfo();
    }

    @PutMapping("/users/{userId}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable UUID userId, @RequestBody org.example.aa__backend.domain.admin.payload.RoleDTO dto) {
        adminUserRoleUpdateService.updateUserRole(userId, dto.getRole());
        return ResponseEntity.ok("User role updated successfully to: " + dto.getRole());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        adminUserDeleteService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/users/{userId}/info")
    public ResponseEntity<?> updateUserInfo(@PathVariable UUID userId, @RequestBody String email) {
        adminUserInfoUpdateService.updateUserInfo(userId, email);
        return ResponseEntity.ok("User info updated successfully");
    }

    @PutMapping("/users/{userId}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable UUID userId, @RequestBody String newPassword) {
        adminUserPasswordUpdateService.updateUserPassword(userId, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
} 
