package com.authentication.demo.review;

import java.util.List;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.advice.BadRequestException;
import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.review.request.CreateReviewRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final EmployerRepository employerRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
            EmployerRepository employerRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
    }

    public void createReview(CreateReviewRequest request) {
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found."));

        Employer employer = this.employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new NotFoundException("Employer not found."));

        List<Review> reviews = this.reviewRepository.getReviewByUserAndEmployer(request.getUserId(),
                request.getEmployerId());

        System.out.println(reviews.size());
        if (reviews.size() >= 1) {
            throw new BadRequestException("You have already reviewed this company");
        }
        Review review = new Review();
        review.setText(request.getText());
        review.setRating(request.getRating());
        review.setEmployer(employer);
        review.setUser(user);

        this.reviewRepository.save(review);
    }
}
