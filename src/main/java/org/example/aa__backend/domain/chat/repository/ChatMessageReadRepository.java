package org.example.aa__backend.domain.chat.repository;

import org.example.aa__backend.domain.chat.entity.ChatMessageRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatMessageReadRepository extends JpaRepository<ChatMessageRead, Long> {
    boolean existsByMessageIdAndReaderId(Long messageId, UUID readerId);
}

