package org.example.aa__backend.domain.user.repository;

import org.example.aa__backend.domain.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
    List<Account> findByNameContainingIgnoreCase(String name);
} 
