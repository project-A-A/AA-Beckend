package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminUserRoleUpdateService;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserRoleUpdateServiceImpl implements AdminUserRoleUpdateService {
    
    private final AccountRepository accountRepository;

    @Override
    public void updateUserRole(Long userId, String newRole) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        account.setRole(newRole);
        accountRepository.save(account);
        log.info("User role updated by admin successfully: userId={}, newRole={}", userId, newRole);
    }
} 
