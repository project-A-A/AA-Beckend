package org.example.aa__backend.domain.coffeechat.repository;

import org.example.aa__backend.domain.coffeechat.entity.CoffeeChatRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CoffeeChatRequestRepository extends JpaRepository<CoffeeChatRequest, Long> {
    List<CoffeeChatRequest> findByRequesterIdOrTargetId(UUID requesterId, UUID targetId);
}


