package org.example.aa__backend.domain.user.service;

import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserQueryService {
    ResponseEntity<List<AccountViewDTO>> getAllUsers();
    ResponseEntity<AccountViewDTO> getUserById(UUID userId);
} 
