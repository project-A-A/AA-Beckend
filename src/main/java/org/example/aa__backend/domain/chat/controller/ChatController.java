package org.example.aa__backend.domain.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.chat.payload.ChatMessageRequest;
import org.example.aa__backend.domain.chat.payload.ChatMessageResponse;
import org.example.aa__backend.domain.chat.payload.ChatReadRequest;
import org.example.aa__backend.domain.chat.payload.ChatRoomSummary;
import org.example.aa__backend.domain.chat.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<ChatRoomSummary>> myRooms(Authentication authentication) {
        return ResponseEntity.ok(chatService.listMyRooms(authentication.getName()));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<ChatMessageResponse>> messages(Authentication authentication,
                                                              @PathVariable UUID roomId) {
        return ResponseEntity.ok(chatService.listMessages(authentication.getName(), roomId));
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<ChatMessageResponse> send(Authentication authentication,
                                                    @PathVariable UUID roomId,
                                                    @Valid @RequestBody ChatMessageRequest request) {
        ChatMessageResponse response = chatService.sendMessage(authentication.getName(), roomId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{roomId}/read")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void read(Authentication authentication,
                     @PathVariable UUID roomId,
                     @Valid @RequestBody ChatReadRequest request) {
        chatService.markRead(authentication.getName(), roomId, request);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leave(Authentication authentication, @PathVariable UUID roomId) {
        chatService.leaveRoom(authentication.getName(), roomId);
    }
}

