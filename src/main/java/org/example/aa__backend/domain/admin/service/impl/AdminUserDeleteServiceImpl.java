package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminUserDeleteService;
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
public class AdminUserDeleteServiceImpl implements AdminUserDeleteService {
    
    private final AccountRepository accountRepository;

    @Override
    public void deleteUser(Long userId) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        accountRepository.delete(account);
        log.info("User deleted by admin successfully: userId={}", userId);
    }
} 
