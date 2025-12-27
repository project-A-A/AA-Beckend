package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminUserInfoUpdateService;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserInfoUpdateServiceImpl implements AdminUserInfoUpdateService {
    
    private final AccountRepository accountRepository;

    @Override
    public void updateUserInfo(UUID userId, String email) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        account.setEmail(email);
        accountRepository.save(account);
        log.info("User info updated by admin successfully: userId={}, email={}", userId, email);
    }
} 
