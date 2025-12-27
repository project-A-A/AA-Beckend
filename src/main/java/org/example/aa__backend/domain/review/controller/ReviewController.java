package org.example.aa__backend.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.aa__backend.domain.review.payload.ReviewRequest;
import org.example.aa__backend.domain.review.payload.ReviewResponse;
import org.example.aa__backend.domain.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/review")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> list(@PathVariable UUID userId) {
        return ResponseEntity.ok(reviewService.listReviews(userId));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> get(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReview(reviewId));
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> create(Authentication authentication,
                                                 @PathVariable UUID userId,
                                                 @Valid @RequestBody ReviewRequest request) {
        ReviewResponse response = reviewService.createReview(authentication.getName(), userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

