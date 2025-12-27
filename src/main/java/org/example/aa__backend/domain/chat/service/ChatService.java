package org.example.aa__backend.domain.chat.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.chat.entity.ChatMessage;
import org.example.aa__backend.domain.chat.entity.ChatMessageRead;
import org.example.aa__backend.domain.chat.entity.ChatParticipant;
import org.example.aa__backend.domain.chat.entity.ChatRoom;
import org.example.aa__backend.domain.chat.payload.ChatMessageRequest;
import org.example.aa__backend.domain.chat.payload.ChatMessageResponse;
import org.example.aa__backend.domain.chat.payload.ChatReadRequest;
import org.example.aa__backend.domain.chat.payload.ChatRoomSummary;
import org.example.aa__backend.domain.chat.repository.ChatMessageReadRepository;
import org.example.aa__backend.domain.chat.repository.ChatMessageRepository;
import org.example.aa__backend.domain.chat.repository.ChatParticipantRepository;
import org.example.aa__backend.domain.chat.repository.ChatRoomRepository;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageReadRepository chatMessageReadRepository;
    private final AccountRepository accountRepository;

    public List<ChatRoomSummary> listMyRooms(String email) {
        Account me = requireAccount(email);
        List<ChatParticipant> myMemberships = chatParticipantRepository.findByAccountId(me.getId());

        return myMemberships.stream()
                .map(ChatParticipant::getRoom)
                .distinct()
                .map(room -> {
                    var lastMessage = chatMessageRepository.findTop1ByRoomIdOrderBySentAtDesc(room.getId()).orElse(null);
                    List<UUID> participantIds = room.getParticipants()
                            .stream()
                            .map(p -> p.getAccount().getId())
                            .collect(Collectors.toList());
                    return ChatRoomSummary.builder()
                            .roomId(room.getId())
                            .participantIds(participantIds)
                            .lastMessageId(lastMessage != null ? lastMessage.getId() : null)
                            .lastMessage(lastMessage != null ? lastMessage.getContent() : null)
                            .lastSentAt(lastMessage != null ? lastMessage.getSentAt() : null)
                            .build();
                })
                .toList();
    }

    public List<ChatMessageResponse> listMessages(String email, UUID roomId) {
        Account me = requireAccount(email);
        ensureMembership(roomId, me.getId());

        return chatMessageRepository.findTop50ByRoomIdOrderBySentAtDesc(roomId).stream()
                .sorted((a, b) -> a.getSentAt().compareTo(b.getSentAt()))
                .map(this::toResponse)
                .toList();
    }

    public ChatMessageResponse sendMessage(String email, UUID roomId, ChatMessageRequest request) {
        Account me = requireAccount(email);
        ensureMembership(roomId, me.getId());

        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Chat room not found"));

        ChatMessage message = new ChatMessage();
        message.setRoom(room);
        message.setSender(me);
        message.setContent(request.getContent());
        message.setSentAt(Instant.now());

        ChatMessage saved = chatMessageRepository.save(message);

        markReadInternal(saved, me);

        return toResponse(saved);
    }

    public void markRead(String email, UUID roomId, ChatReadRequest request) {
        Account me = requireAccount(email);
        ensureMembership(roomId, me.getId());

        ChatMessage message = chatMessageRepository.findById(request.getMessageId())
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));

        if (!message.getRoom().getId().equals(roomId)) {
            throw new AccessDeniedException("Message does not belong to room");
        }

        markReadInternal(message, me);
    }

    public void leaveRoom(String email, UUID roomId) {
        Account me = requireAccount(email);
        ensureMembership(roomId, me.getId());
        chatParticipantRepository.deleteByRoomIdAndAccountId(roomId, me.getId());
    }

    private void ensureMembership(UUID roomId, UUID accountId) {
        boolean member = chatParticipantRepository.existsByRoomIdAndAccountId(roomId, accountId);
        if (!member) {
            throw new AccessDeniedException("Not a participant of this room");
        }
    }

    private ChatMessageResponse toResponse(ChatMessage message) {
        return ChatMessageResponse.builder()
                .id(message.getId())
                .roomId(message.getRoom().getId())
                .senderId(message.getSender().getId())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .build();
    }

    private Account requireAccount(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    private void markReadInternal(ChatMessage message, Account reader) {
        boolean alreadyRead = chatMessageReadRepository.existsByMessageIdAndReaderId(message.getId(), reader.getId());
        if (alreadyRead) {
            return;
        }
        ChatMessageRead read = new ChatMessageRead();
        read.setMessage(message);
        read.setReader(reader);
        read.setReadAt(Instant.now());
        chatMessageReadRepository.save(read);
    }
}

