package org.example.aa__backend.domain.review.repository;

import org.example.aa__backend.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRevieweeId(UUID revieweeId);

    long countByRevieweeId(UUID revieweeId);

    @Query("select coalesce(avg(r.score), 0) from Review r where r.reviewee.id = :revieweeId")
    Double averageScoreByRevieweeId(@Param("revieweeId") UUID revieweeId);
}

