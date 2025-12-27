package org.example.aa__backend.domain.chat.repository;

import org.example.aa__backend.domain.chat.entity.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {
    List<ChatParticipant> findByAccountId(UUID accountId);
    boolean existsByRoomIdAndAccountId(UUID roomId, UUID accountId);
    void deleteByRoomIdAndAccountId(UUID roomId, UUID accountId);
}

