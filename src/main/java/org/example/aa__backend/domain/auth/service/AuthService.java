package org.example.aa__backend.domain.auth.service;

import org.example.aa__backend.domain.auth.payload.TokenDTO;
import org.example.aa__backend.domain.auth.payload.UserLoginDTO;

public interface AuthService {
    TokenDTO authenticate(UserLoginDTO userLogin);
} 
