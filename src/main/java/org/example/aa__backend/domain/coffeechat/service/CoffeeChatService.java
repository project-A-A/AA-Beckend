package org.example.aa__backend.domain.coffeechat.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.coffeechat.entity.CoffeeChatRequest;
import org.example.aa__backend.domain.coffeechat.entity.CoffeeChatStatus;
import org.example.aa__backend.domain.coffeechat.payload.CoffeeChatCreateRequest;
import org.example.aa__backend.domain.coffeechat.payload.CoffeeChatResponse;
import org.example.aa__backend.domain.coffeechat.repository.CoffeeChatRequestRepository;
import org.example.aa__backend.domain.user.entity.Account;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeChatService {

    private final CoffeeChatRequestRepository coffeeChatRequestRepository;
    private final AccountRepository accountRepository;

    public CoffeeChatResponse request(String requesterEmail, UUID targetUserId, CoffeeChatCreateRequest request) {
        Account requester = requireAccount(requesterEmail);
        Account target = accountRepository.findById(targetUserId)
                .orElseThrow(() -> new EntityNotFoundException("Target user not found"));

        if (requester.getId().equals(target.getId())) {
            throw new AccessDeniedException("Cannot request coffee chat with yourself");
        }

        CoffeeChatRequest entity = new CoffeeChatRequest();
        entity.setRequester(requester);
        entity.setTarget(target);
        entity.setStatus(CoffeeChatStatus.PENDING);
        entity.setMessage(request.getMessage());
        entity.setCreatedAt(Instant.now());

        CoffeeChatRequest saved = coffeeChatRequestRepository.save(entity);
        return toResponse(saved);
    }

    public List<CoffeeChatResponse> list(String requesterEmail, UUID userId) {
        Account me = requireAccount(requesterEmail);
        if (!me.getId().equals(userId)) {
            throw new AccessDeniedException("Cannot view other users' coffee chats");
        }
        return coffeeChatRequestRepository.findByRequesterIdOrTargetId(userId, userId).stream()
                .map(this::toResponse)
                .toList();
    }

    private CoffeeChatResponse toResponse(CoffeeChatRequest request) {
        return CoffeeChatResponse.builder()
                .id(request.getId())
                .requesterId(request.getRequester().getId())
                .targetId(request.getTarget().getId())
                .status(request.getStatus())
                .message(request.getMessage())
                .createdAt(request.getCreatedAt())
                .build();
    }

    private Account requireAccount(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }
}


