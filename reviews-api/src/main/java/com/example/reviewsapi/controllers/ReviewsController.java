package com.example.reviewsapi.controllers;


import com.example.reviewsapi.models.Review;
import com.example.reviewsapi.repositories.ReviewRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public Iterable<Review> findAllReviews() {
        System.out.println("inside get all controller");
        return reviewRepository.findAll();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    @GetMapping("/{reviewId}")
    public Review findReviewById(@PathVariable Long reviewId) throws NotFoundException {

        Review foundReview = reviewRepository.findOne(reviewId);

        if (foundReview == null) {
            throw new NotFoundException("this sucks but review with ID of " + reviewId + " is not there!");
        }
        return foundReview;
    }

    @DeleteMapping("/{reviewId}")
    public HttpStatus deleteReviewById(@PathVariable Long reviewId) throws EmptyResultDataAccessException {
        reviewRepository.delete(reviewId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Review createNewReview(@RequestBody Review newReview) {

        return reviewRepository.save(newReview);
    }
    @PutMapping("/{reviewId}")
    public Review putReviewById(@PathVariable Long reviewId, @RequestBody Review reviewRequest) throws NotFoundException{
        Review reviewFromDb = reviewRepository.findOne(reviewId);
        System.out.println("inside put controller" + reviewId + reviewRequest);


        if (reviewFromDb == null) {
            throw new NotFoundException("Review with ID of "+ reviewId + " was not found");
        }

        reviewFromDb.setUser_id(reviewRequest.getUser_id());
        reviewFromDb.setCamis(reviewRequest.getCamis());
        reviewFromDb.setDba(reviewRequest.getDba());
        reviewFromDb.setBldg(reviewRequest.getBldg());
        reviewFromDb.setStreet(reviewRequest.getStreet());
        reviewFromDb.setBoro(reviewRequest.getBoro());
        reviewFromDb.setZip(reviewRequest.getZip());
        reviewFromDb.setReview(reviewRequest.getReview());
        reviewFromDb.setRating(reviewRequest.getRating());
        reviewFromDb.setGrade(reviewRequest.getGrade());
        reviewFromDb.setCategory(reviewRequest.getCategory());

        return reviewRepository.save(reviewFromDb);
    }


//    @PatchMapping("/{reviewId}")
//    public Review updateReviewById(@PathVariable Long reviewId, @RequestBody Review reviewRequest) throws NotFoundException{
//        Review reviewFromDb = reviewRepository.findOne(reviewId);
//        System.out.println("inside patch controller" + reviewId + reviewRequest);
//
//
//        if (reviewFromDb == null) {
//            throw new NotFoundException("Review with ID of "+ reviewId + " was not found");
//        }
//
//        reviewFromDb.setUser_id(reviewRequest.getUser_id());
//        reviewFromDb.setCamis(reviewRequest.getCamis());
//        reviewFromDb.setDba(reviewRequest.getDba());
//        reviewFromDb.setBldg(reviewRequest.getBldg());
//        reviewFromDb.setStreet(reviewRequest.getStreet());
//        reviewFromDb.setBoro(reviewRequest.getBoro());
//        reviewFromDb.setZip(reviewRequest.getZip());
//        reviewFromDb.setReview(reviewRequest.getReview());
//        reviewFromDb.setRating(reviewRequest.getRating());
//        reviewFromDb.setGrade(reviewRequest.getGrade());
//        reviewFromDb.setCategory(reviewRequest.getCategory());
//
//        return reviewRepository.save(reviewFromDb);
//    }





    // EXCEPTION HANDLERS

    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
