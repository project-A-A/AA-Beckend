package org.example.aa__backend.domain.user.repository;

import org.example.aa__backend.domain.user.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
    List<Account> findByNameContainingIgnoreCase(String name);
    List<Account> findByMajorContainingIgnoreCase(String major);
    List<Account> findByNameContainingIgnoreCaseOrMajorContainingIgnoreCase(String name, String major);

    @Query("select distinct a.major from Account a")
    List<String> findDistinctMajors();
} 
