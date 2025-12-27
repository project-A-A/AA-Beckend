package org.example.aa__backend.domain.chat.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatReadRequest {
    @NotNull
    private Long messageId;
}

