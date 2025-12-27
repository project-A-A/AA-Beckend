package org.example.aa__backend.domain.user.payload;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CredenceResponse {
    private final UUID userId;
    private final double averageScore;
    private final long reviewCount;
}


