package org.example.aa__backend.domain.chat.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ReadReceipt {
    private UUID roomId;
    private Long messageId;
    private UUID readerId;
    private Instant readAt = Instant.now();
}

