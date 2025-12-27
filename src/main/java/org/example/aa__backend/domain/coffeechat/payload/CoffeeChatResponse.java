package org.example.aa__backend.domain.coffeechat.payload;

import lombok.Builder;
import lombok.Getter;
import org.example.aa__backend.domain.coffeechat.entity.CoffeeChatStatus;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class CoffeeChatResponse {
    private final Long id;
    private final UUID requesterId;
    private final UUID targetId;
    private final CoffeeChatStatus status;
    private final String message;
    private final Instant createdAt;
}


