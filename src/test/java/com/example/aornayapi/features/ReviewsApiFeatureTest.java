package com.example.aornayapi.features;

import com.example.aornayapi.models.Review;
import com.example.aornayapi.repositories.ReviewRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;


import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReviewsApiFeatureTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        reviewRepository.deleteAll();
    }

    @After
    public void tearDown() {
        reviewRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForAUser() throws Exception {
        Review firstReview = new Review(
                12375757757L,
                "dba",
                "bldg",
                "dba",
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
                "dba",
                "bldg",
                "dba",
                "street",
                "boro",
                "zip",
                "review",
                2,
                "grade",
                "category"
        );

        Stream.of(firstReview, secondReview)
                .forEach(review -> {
                    reviewRepository.save(review);
                });
        when()
                .get("http://localhost:8080/reviews/")
                .then()
                .statusCode(is(200))
                .and().body(containsString("dba"))
                .and().body(containsString("street"));

        // Test creating a User
        Review reviewNotYetInDb = new Review(
                3L,
                "new_user",
                "Not",
                "Yet Created",
                "st",
                "borough",
                "code",
                "rev",
                3,
                "grad",
                "cat"
        );

        given()
                .contentType(JSON)
                .and().body(reviewNotYetInDb)
                .when()
                .post("http://localhost:8080/reviews")
                .then()
                .statusCode(is(200))
                .and().body(containsString("new_review"));

// Test get all Users
        when()
                .get("http://localhost:8080/reviews/")
                .then()
                .statusCode(is(200))
                .and().body(containsString("someone"))
                .and().body(containsString("Else"))
                .and().body(containsString("Yet Created"));

// Test finding one user by ID
        when()
                .get("http://localhost:8080/reviews/" + secondReview.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("Someone"))
                .and().body(containsString("Else"));

// Test updating a user
        secondReview.setCamis("changed_name");

        given()
                .contentType(JSON)
                .and().body(secondReview)
                .when()
                .patch("http://localhost:8080/reviews/" + secondReview.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("changed_name"));

// Test deleting a user
        when()
                .delete("http://localhost:8080/reviews/" + secondReview.getId())
                .then()
                .statusCode(is(200));


    }

//        this.user_id = user_id;
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








}
