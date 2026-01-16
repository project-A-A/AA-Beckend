package org.example.aa__backend.domain.admin.service.impl;

import org.example.aa__backend.domain.admin.service.AdminUserQueryService;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserQueryServiceImpl implements AdminUserQueryService {
    
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<List<AccountViewDTO>> getAllUsers() {
        try {
            List<Account> accounts = accountRepository.findAll();
            List<AccountViewDTO> userList = accounts.stream()
                .map(account -> new AccountViewDTO(account.getId(), account.getEmail(), account.getName(), account.getMajor(), account.getRole()))
                .collect(Collectors.toList());
            
            log.info("All users retrieved by admin successfully: count={}", userList.size());
            return ResponseEntity.ok(userList);
        } catch (Exception e) {
            log.error("Failed to get all users by admin: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getUserStatistics() {
        try {
            List<Account> accounts = accountRepository.findAll();
            
            long totalUsers = accounts.size();
            long adminUsers = accounts.stream()
                .filter(account -> "ROLE_ADMIN".equals(account.getRole()))
                .count();
            long regularUsers = totalUsers - adminUsers;
            
            Map<String, Object> statistics = new HashMap<>();
            statistics.put("totalUsers", totalUsers);
            statistics.put("adminUsers", adminUsers);
            statistics.put("regularUsers", regularUsers);
            statistics.put("adminPercentage", totalUsers > 0 ? (double) adminUsers / totalUsers * 100 : 0);
            
            log.info("User statistics retrieved by admin successfully: totalUsers={}, adminUsers={}", totalUsers, adminUsers);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            log.error("Failed to get user statistics by admin: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 
