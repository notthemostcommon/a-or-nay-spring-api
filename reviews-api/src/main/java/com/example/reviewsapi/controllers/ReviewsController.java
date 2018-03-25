package com.example.reviewsapi.controllers;


import com.example.reviewsapi.models.Review;
import com.example.reviewsapi.repositories.ReviewRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public Iterable<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{reviewId}")
    public Review findReviewById(@PathVariable Long reviewId) throws NotFoundException {
        Review foundReview = reviewRepository.findOne(reviewId);

        if (foundReview == null) {
            throw new NotFoundException("this sucks but review with ID of " + reviewId + " is not there!");
        }
        return foundReview;
    }

    // EXCEPTION HANDLERS

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
