package org.example.aa__backend.domain.notice.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class NoticeResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final Instant createdAt;
    private final Instant updatedAt;
}


