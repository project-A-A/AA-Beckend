package org.example.aa__backend.global.config;

import org.example.aa__backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

//@Configuration
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Seed initial data here using userService if needed.
        System.out.println("SeedData initialization completed");
    }
} 
