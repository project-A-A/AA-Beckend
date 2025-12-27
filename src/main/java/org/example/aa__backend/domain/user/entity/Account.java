package org.example.aa__backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    private String password;

    // Examples: ROLE_USER, ROLE_ADMIN, ROLE_MANAGER
    private String role;
} 
