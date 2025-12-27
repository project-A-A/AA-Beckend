package org.example.aa__backend.domain.review.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.review.entity.Review;
import org.example.aa__backend.domain.review.payload.ReviewRequest;
import org.example.aa__backend.domain.review.payload.ReviewResponse;
import org.example.aa__backend.domain.review.repository.ReviewRepository;
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
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AccountRepository accountRepository;

    public List<ReviewResponse> listReviews(UUID userId) {
        return reviewRepository.findByRevieweeId(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    public ReviewResponse getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        return toResponse(review);
    }

    public ReviewResponse createReview(String reviewerEmail, UUID revieweeId, ReviewRequest request) {
        Account reviewer = accountRepository.findByEmail(reviewerEmail)
                .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));
        Account reviewee = accountRepository.findById(revieweeId)
                .orElseThrow(() -> new EntityNotFoundException("Reviewee not found"));

        if (reviewer.getId().equals(reviewee.getId())) {
            throw new AccessDeniedException("Cannot review yourself");
        }

        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setScore(request.getScore());
        review.setComment(request.getComment());
        review.setPrivateReview(request.isPrivateReview());
        review.setCreatedAt(Instant.now());

        Review saved = reviewRepository.save(review);
        return toResponse(saved);
    }

    private ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewerId(review.getReviewer().getId())
                .revieweeId(review.getReviewee().getId())
                .score(review.getScore())
                .comment(review.getComment())
                .privateReview(review.isPrivateReview())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

