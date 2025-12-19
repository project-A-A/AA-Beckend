package org.example.aa__backend.domain.user.controller;

import org.example.aa__backend.domain.user.payload.AccountDTO;
import org.example.aa__backend.domain.user.service.UserQueryService;
import org.example.aa__backend.domain.user.service.UserSelfUpdateService;
import org.example.aa__backend.domain.user.service.UserSelfPasswordUpdateService;
import org.example.aa__backend.domain.user.service.UserSelfDeleteService;
import org.example.aa__backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name="User Controller", description = "Controller for User management")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;
    private final UserSelfUpdateService userSelfUpdateService;
    private final UserSelfPasswordUpdateService userSelfPasswordUpdateService;
    private final UserSelfDeleteService userSelfDeleteService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addUser(@RequestBody AccountDTO accountDTO){
        String result = userService.createUser(accountDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/home")
    public String home() {
        return "Well come! My frend!!";
    }

    @GetMapping("/profile/{userId}")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> getMyProfile(@PathVariable Long userId) {
        return userQueryService.getUserById(userId);
    }

    @PutMapping("/profile/{userId}/info")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> updateMyInfo(@PathVariable Long userId, @RequestBody String email) {
        userSelfUpdateService.updateSelfInfo(userId, email);
        return ResponseEntity.ok("Your information updated successfully");
    }

    @PutMapping("/profile/{userId}/password")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> updateMyPassword(@PathVariable Long userId, 
                                            @RequestBody String currentPassword,
                                            @RequestParam String newPassword) {
        userSelfPasswordUpdateService.updateSelfPassword(userId, currentPassword, newPassword);
        return ResponseEntity.ok("Your password updated successfully");
    }

    @DeleteMapping("/profile/{userId}")
    @SecurityRequirement(name = "security-demo-api")
    public ResponseEntity<?> deleteMyAccount(@PathVariable Long userId, @RequestBody String password) {
        userSelfDeleteService.deleteSelfAccount(userId, password);
        return ResponseEntity.ok("Your account deleted successfully");
    }
} 
