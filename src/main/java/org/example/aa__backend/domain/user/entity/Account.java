package org.example.aa__backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    // Examples: ROLE_USER, ROLE_ADMIN, ROLE_MANAGER
    private String role;
} 
