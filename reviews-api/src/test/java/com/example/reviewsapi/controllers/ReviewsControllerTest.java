package com.example.reviewsapi.controllers;

import com.example.reviewsapi.models.Review;
import com.example.reviewsapi.repositories.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewsController.class)
public class ReviewsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewRepository mockReviewRepository;

    @Before
    public void setUp() {
        Review firstReview = new Review(
                12375757757L,
                "camis",
                "dba",
                "bldg",
                "street",
                "boro",
                "zip",
                "review",
                3,
                "grade",
                "category"
        );

        Review secondReview = new Review(
                12344444L,
                "camis2",
                "dba2",
                "bldg2",
                "street2",
                "boro2",
                "zip2",
                "review2",
                2,
                "grade2",
                "category2"
        );

        Iterable<Review> mockReviews =
                Stream.of(firstReview, secondReview).collect(Collectors.toList());

        given(mockReviewRepository.findAll()).willReturn(mockReviews);
        given(mockReviewRepository.findOne(1L)).willReturn(firstReview);
        given(mockReviewRepository.findOne(4L)).willReturn(null);
        // Mock out Delete to return EmptyResultDataAccessException for missing user with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockReviewRepository).delete(4L);

    }

    @Test
    public void findAllReviews_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllReviews_success_returnAllReviewsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllReviews_success_returnCamisForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].camis", is("camis")));
    }

    @Test
    public void findAllReviews_success_returnUseridForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].user_id", is(12375757757L)));
    }

    @Test
    public void findAllReviews_success_returnBldgForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].bldg", is("bldg")));
    }

    @Test
    public void findAllReviews_success_returnStreetForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].street", is("street")));
    }

    @Test
    public void findAllReviews_success_returnBoroForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].boro", is("boro")));
    }

    @Test
    public void findAllReviews_success_returnZipForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].zip", is("zip")));
    }

    @Test
    public void findAllReviews_success_returnReviewForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].review", is("review")));
    }

    @Test
    public void findAllReviews_success_returnRatingForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].rating", is(3)));
    }

    @Test
    public void findAllReviews_success_returnGradeForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].grade", is("grade")));
    }

    @Test
    public void findAllReviews_success_returnCategoryForEachReview() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].category", is("category")));
    }

    @Test
    public void findReviewById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findReviewById_success_returnUserId() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.user_id", is(12375757757L)));
    }

    @Test
    public void findReviewById_success_returnCamis() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.camis", is("camis")));
    }

    @Test
    public void findReviewById_success_returnDba() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.dba", is("dba")));
    }

    @Test
    public void findReviewById_success_returnBldg() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.bldg", is("bldg")));
    }

    @Test
    public void findReviewById_success_returnStreet() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.street", is("street")));
    }

    @Test
    public void findReviewById_success_returnBoro() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.boro", is("boro")));
    }

    @Test
    public void findReviewById_success_returnZip() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.zip", is("zip")));
    }

    @Test
    public void findReviewById_success_returnReview() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.review", is("review")));
    }

    @Test
    public void findReviewById_success_returnRating() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.rating", is(3)));
    }

    @Test
    public void findReviewById_success_returnGrade() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.grade", is("grade")));
    }

    @Test
    public void findReviewById_success_returnCategory() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.category", is("category")));
    }

    @Test
    public void findReviewById_failure_reviewNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findReviewById_failure_reviewNotFoundReturnNotFounErrorMessage() throws Exception {
        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("this sucks but review with ID of 4 is not there!")));
    }

    @Test
    public void deleteReviewById_success_returnStatusOk() throws Exception{
        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteReviewById_success_deleteViaRepository() throws Exception {
        this.mockMvc.perform(delete("/1"));
        verify(mockReviewRepository, times(1)).delete(1L);
    }

    @Test
    public void deleteReviewById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/4"))
                .andExpect(status().isNotFound());
    }







}

//this.user_id = user_id;
//        this.camis = camis;
//        this.dba = dba;
//        this.bldg = bldg;
//        this.street = street;
//        this.boro = boro;
//        this.zip = zip;
//        this.review = review;
//        this.rating = rating;
//        this.grade = grade;
//        this.category = category;