package org.example.aa__backend.domain.chat.repository;

import org.example.aa__backend.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findTop50ByRoomIdOrderBySentAtDesc(UUID roomId);
    Optional<ChatMessage> findTop1ByRoomIdOrderBySentAtDesc(UUID roomId);
}

