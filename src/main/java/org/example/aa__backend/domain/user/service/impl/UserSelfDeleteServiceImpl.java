package org.example.aa__backend.domain.user.service.impl;

import org.example.aa__backend.domain.user.service.UserSelfDeleteService;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSelfDeleteServiceImpl implements UserSelfDeleteService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void deleteSelfAccount(Long userId, String password) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        // Verify password before deletion
        if (!passwordEncoder.matches(password, account.getPassword())) {
            log.warn("Password mismatch for self account deletion: userId={}", userId);
            throw new IllegalArgumentException("Password is incorrect");
        }
        accountRepository.delete(account);
        log.info("User self account deleted successfully: userId={}", userId);
    }
} 
