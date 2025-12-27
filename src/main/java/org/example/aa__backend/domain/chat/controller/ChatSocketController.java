package org.example.aa__backend.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.chat.payload.PresenceEvent;
import org.example.aa__backend.domain.chat.payload.ReadReceipt;
import org.example.aa__backend.domain.chat.payload.TypingEvent;
import org.example.aa__backend.domain.chat.service.PresenceService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final PresenceService presenceService;

    @MessageMapping("/chat/{roomId}/typing")
    public void typing(@DestinationVariable UUID roomId, TypingEvent event) {
        event.setRoomId(roomId);
        messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/typing", event);
    }

    @MessageMapping("/chat/{roomId}/read")
    public void read(@DestinationVariable UUID roomId, ReadReceipt receipt) {
        receipt.setRoomId(roomId);
        messagingTemplate.convertAndSend("/topic/chat/" + roomId + "/read", receipt);
    }

    @MessageMapping("/presence")
    public void presence(PresenceEvent event) {
        presenceService.update(event);
        messagingTemplate.convertAndSend("/topic/presence", event);
    }
}

