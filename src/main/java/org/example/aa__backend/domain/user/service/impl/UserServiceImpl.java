package org.example.aa__backend.domain.user.service.impl;

import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.payload.AccountDTO;
import org.example.aa__backend.domain.user.payload.AccountViewDTO;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import org.example.aa__backend.domain.user.service.UserService;
import org.example.aa__backend.domain.user.validation.UserValidationService;
import org.example.aa__backend.global.util.constants.AccountSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;

    @Override
    public String createUser(AccountDTO accountDTO) {
        userValidationService.validateEmailNotExists(accountDTO.getEmail());

        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setName(accountDTO.getName());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setRole("ROLE_USER");

        accountRepository.save(account);
        return AccountSuccess.ACCOUNT_ADDED.toString();
    }

    @Override
    public List<AccountViewDTO> getAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        
        // Validate that accounts exist
        userValidationService.validateUsersExist(accounts);
        
        return accounts.stream()
                .map(account -> new AccountViewDTO(account.getId(), account.getEmail(), account.getName(), account.getRole()))
                .toList();
    }
} 
