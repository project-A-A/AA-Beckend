package org.example.aa__backend.domain.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.review.repository.ReviewRepository;
import org.example.aa__backend.domain.user.payload.CredenceResponse;
import org.example.aa__backend.domain.user.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CredenceService {

    private final ReviewRepository reviewRepository;
    private final AccountRepository accountRepository;

    public CredenceResponse getCredence(UUID userId) {
        accountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        long count = reviewRepository.countByRevieweeId(userId);
        double average = reviewRepository.averageScoreByRevieweeId(userId);

        return CredenceResponse.builder()
                .userId(userId)
                .averageScore(average)
                .reviewCount(count)
                .build();
    }
}


