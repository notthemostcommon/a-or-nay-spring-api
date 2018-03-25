package com.example.reviewsapi.controllers;


import com.example.reviewsapi.models.Review;
import com.example.reviewsapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public Iterable<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Review findReviewById(@PathVariable Long userId) {
        return reviewRepository.findOne(userId);
    }
}


