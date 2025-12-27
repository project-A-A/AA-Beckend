package org.example.aa__backend.domain.coffeechat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.aa__backend.domain.user.entity.Account;

import java.time.Instant;

@Entity
@Getter
@Setter
public class CoffeeChatRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_id")
    private Account requester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_id")
    private Account target;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoffeeChatStatus status = CoffeeChatStatus.PENDING;

    @Column(length = 1000)
    private String message;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}


