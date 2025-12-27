package org.example.aa__backend.domain.user.service.impl;

import org.example.aa__backend.domain.user.service.UserSelfPasswordUpdateService;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSelfPasswordUpdateServiceImpl implements UserSelfPasswordUpdateService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateSelfPassword(UUID userId, String currentPassword, String newPassword) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        // Verify current password
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            log.warn("Current password mismatch for user: userId={}", userId);
            throw new IllegalArgumentException("Current password is incorrect");
        }
        // Update to new password
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
        log.info("User self password updated successfully: userId={}", userId);
    }
} 
