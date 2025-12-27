package org.example.aa__backend.domain.chat.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    @NotBlank
    private String content;
}

