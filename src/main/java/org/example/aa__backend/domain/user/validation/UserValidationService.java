package org.example.aa__backend.domain.user.validation;

import org.example.aa__backend.global.exception.UserCreationException;
import org.example.aa__backend.global.exception.UserNotFoundException;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {

    private final AccountRepository accountRepository;

    public void validateEmailNotExists(String email) {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new UserCreationException("Email already exists.");
        }
    }

    public void validateUsersExist(List<?> users) {
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
    }
} 
