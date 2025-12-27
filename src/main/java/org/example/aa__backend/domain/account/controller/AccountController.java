package org.example.aa__backend.domain.account.controller;

import org.example.aa__backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final UserService userService;

    @GetMapping("/")
    public String demo(){
        return "Hello world";
    }

    @GetMapping("/test")
    public String test(){
        return "Test Api";
    }
} 
