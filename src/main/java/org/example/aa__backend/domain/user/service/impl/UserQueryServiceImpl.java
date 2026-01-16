package org.example.aa__backend.domain.user.service.impl;

import org.example.aa__backend.domain.user.service.UserQueryService;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryServiceImpl implements UserQueryService {
    
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<List<AccountViewDTO>> getAllUsers() {
        try {
            List<Account> accounts = accountRepository.findAll();
            List<AccountViewDTO> userList = accounts.stream()
                .map(account -> new AccountViewDTO(account.getId(), account.getEmail(), account.getName(), account.getMajor(), account.getRole()))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            log.error("Failed to get all users: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AccountViewDTO> getUserById(UUID userId) {
        try {
            Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            AccountViewDTO userInfo = new AccountViewDTO(account.getId(), account.getEmail(), account.getName(), account.getMajor(), account.getRole());
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            log.error("Failed to get user by id: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


} 
