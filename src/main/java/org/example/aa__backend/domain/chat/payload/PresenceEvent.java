package org.example.aa__backend.domain.chat.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class PresenceEvent {
    private UUID userId;
    private boolean online;
    private Instant at = Instant.now();
}

