package org.example.aa__backend.domain.review.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class ReviewResponse {
    private final Long id;
    private final UUID reviewerId;
    private final UUID revieweeId;
    private final int score;
    private final String comment;
    private final boolean privateReview;
    private final Instant createdAt;
}

