package org.example.aa__backend.domain.user.service.impl;

import org.example.aa__backend.domain.user.service.UserSelfUpdateService;
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
public class UserSelfUpdateServiceImpl implements UserSelfUpdateService {
    
    private final AccountRepository accountRepository;

    @Override
    public void updateSelfInfo(Long userId, String email) {
        Account account = accountRepository.findById(userId)
            .orElseThrow(() -> new org.example.aa__backend.global.exception.UserNotFoundException("User not found"));
        account.setEmail(email);
        accountRepository.save(account);
        log.info("User self info updated successfully: userId={}, email={}", userId, email);
    }
} 
