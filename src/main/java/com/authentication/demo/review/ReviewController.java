package com.authentication.demo.review;

import com.authentication.demo.review.request.CreateReviewRequest;
import com.authentication.demo.review.response.CreateReviewResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
       this.reviewService = reviewService;
    }

    @PostMapping("/")
    public ResponseEntity<CreateReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        this.reviewService.createReview(request);
          return ResponseEntity.status(HttpStatus.CREATED).body(new CreateReviewResponse("Success"));
    }
}
