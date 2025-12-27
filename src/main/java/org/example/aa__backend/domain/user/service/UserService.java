package org.example.aa__backend.domain.user.service;

import org.example.aa__backend.domain.user.payload.AccountDTO;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;

import java.util.List;

public interface UserService {
    String createUser(AccountDTO accountDTO);
    List<AccountViewDTO> getAllUsers();
} 
