package org.example.aa__backend.domain.chat.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class ChatMessageResponse {
    private final Long id;
    private final UUID roomId;
    private final UUID senderId;
    private final String content;
    private final Instant sentAt;
}

