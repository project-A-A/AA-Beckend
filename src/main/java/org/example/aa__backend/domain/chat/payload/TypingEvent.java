package org.example.aa__backend.domain.chat.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TypingEvent {
    private UUID roomId;
    private UUID userId;
    private boolean typing;
}

