package com.example.reviewsapi.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {

    @GetMapping("/")
    public String findAllReviews() {
        return "It's working!!!";
    }
}
