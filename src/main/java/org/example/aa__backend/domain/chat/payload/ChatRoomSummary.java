package org.example.aa__backend.domain.chat.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ChatRoomSummary {
    private final UUID roomId;
    private final List<UUID> participantIds;
    private final Long lastMessageId;
    private final String lastMessage;
    private final Instant lastSentAt;
}

