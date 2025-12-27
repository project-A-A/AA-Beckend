package org.example.aa__backend.domain.review.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    @Min(0)
    @Max(100)
    private int score;

    @NotBlank
    @Size(max = 1000)
    private String comment;

    private boolean privateReview = false;
}

