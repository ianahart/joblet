package com.authentication.demo.review;

import com.authentication.demo.review.dto.ReviewPaginationDto;
import com.authentication.demo.review.request.CreateReviewRequest;
import com.authentication.demo.review.response.CreateReviewResponse;
import com.authentication.demo.review.response.DeleteReviewResponse;
import com.authentication.demo.review.response.GetAverageRatingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/avg")
    public ResponseEntity<GetAverageRatingResponse> getAvgReviewRating(@RequestParam("employerId") Long employerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetAverageRatingResponse(this.reviewService.getAvgReviewRating(employerId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteReviewResponse> deleteReview(@PathVariable("id") Long id) {
        this.reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DeleteReviewResponse("Success"));
    }

    @GetMapping("/")
    public ResponseEntity<ReviewPaginationDto> getReviews(@RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("direction") String direction) {

        return ResponseEntity.status(HttpStatus.OK).body(this.reviewService.getReviews(page, size, direction));
    }

    @PostMapping("/")
    public ResponseEntity<CreateReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
        this.reviewService.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateReviewResponse("Success"));
    }
}
