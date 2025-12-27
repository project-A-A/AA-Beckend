package org.example.aa__backend.domain.review.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.aa__backend.domain.user.entity.Account;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer_id")
    private Account reviewer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewee_id")
    private Account reviewee;

    @Column(nullable = false)
    private int score;

    @Column(length = 1000)
    private String comment;

    @Column(nullable = false)
    private boolean privateReview = false;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

