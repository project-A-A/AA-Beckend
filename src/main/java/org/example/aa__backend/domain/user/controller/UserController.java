package org.example.aa__backend.domain.user.controller;

import org.example.aa__backend.domain.user.payload.CredenceResponse;
import org.example.aa__backend.domain.user.service.CredenceService;
import org.example.aa__backend.domain.user.service.UserQueryService;
import org.example.aa__backend.domain.user.service.UserSelfUpdateService;
import org.example.aa__backend.domain.user.service.UserSelfPasswordUpdateService;
import org.example.aa__backend.domain.user.service.UserSelfDeleteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name="User Controller", description = "Controller for User management")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserSelfUpdateService userSelfUpdateService;
    private final UserSelfPasswordUpdateService userSelfPasswordUpdateService;
    private final UserSelfDeleteService userSelfDeleteService;
    private final CredenceService credenceService;

    @GetMapping("/{userId}")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getMyProfile(@PathVariable UUID userId) {
        return userQueryService.getUserById(userId);
    }

    @GetMapping("/{userId}/credence")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<CredenceResponse> credence(@PathVariable UUID userId) {
        return ResponseEntity.ok(credenceService.getCredence(userId));
    }

    @PutMapping("/{userId}/info")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> updateMyInfo(@PathVariable UUID userId, @RequestBody String email) {
        userSelfUpdateService.updateSelfInfo(userId, email);
        return ResponseEntity.ok("Your information updated successfully");
    }

    @PutMapping("/{userId}/password")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> updateMyPassword(@PathVariable UUID userId, 
                                            @RequestBody String currentPassword,
                                            @RequestParam String newPassword) {
        userSelfPasswordUpdateService.updateSelfPassword(userId, currentPassword, newPassword);
        return ResponseEntity.ok("Your password updated successfully");
    }

    @DeleteMapping("/{userId}")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> deleteMyAccount(@PathVariable UUID userId, @RequestBody String password) {
        userSelfDeleteService.deleteSelfAccount(userId, password);
        return ResponseEntity.ok("Your account deleted successfully");
    }
} 
