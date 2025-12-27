package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminUserPasswordUpdateService;
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
public class AdminUserPasswordUpdateServiceImpl implements AdminUserPasswordUpdateService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateUserPassword(UUID userId, String newPassword) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
        log.info("User password updated by admin successfully: userId={}", userId);
    }
} 
