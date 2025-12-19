package org.example.aa__backend.domain.admin.service;

import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface AdminUserQueryService {
    ResponseEntity<List<AccountViewDTO>> getAllUsers();
    ResponseEntity<Map<String, Object>> getUserStatistics();
} 
