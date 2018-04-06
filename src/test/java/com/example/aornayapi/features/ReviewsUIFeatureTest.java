//package com.example.aornayapi.features;
//
//import com.example.aornayapi.models.Review;
//import com.example.aornayapi.repositories.ReviewRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.stream.Stream;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.$$;
//import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.CollectionCondition.size;
//import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Selenide.*;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class ReviewsUIFeatureTest {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Before
//    public void setUp() {
//        reviewRepository.deleteAll();
//    }
//
//    @After
//    public void tearDown() {
//        reviewRepository.deleteAll();
//    }
//
//    @Test
//    public void shouldAllowFullCrudFunctionalityForAReview() throws Exception {
//        Review firstReview = new Review(
//                12375757757L,
//                "camis",
//                "dba",
//                "bldg",
//                "street",
//                "boro",
//                "zip",
//                "review",
//                3,
//                "grade",
//                "category"
//        );
//        firstReview = reviewRepository.save(firstReview);
//        Long firstReviewId = firstReview.getId();
//
//
//        Review secondReview = new Review(
//                12344444L,
//                "camis2",
//                "dba2",
//                "bldg2",
//                "street2",
//                "boro2",
//                "zip2",
//                "review2",
//                2,
//                "grade2",
//                "category2"
//        );
//
//        secondReview = reviewRepository.save(secondReview);
//        Long secondReviewId = secondReview.getId();
//
//        open("http://localhost:3000");
//        System.setProperty("selenide.browser", "Chrome");
//        System.setProperty("selenide.headless", "true");
//
//        $$("[data-review-display]").shouldHave(size(2));
//
//        $("#review-" + firstReviewId + "-camis").shouldHave(text("camis"));
//        $("#review-" + firstReviewId + "-dba").shouldHave(text("dba"));
//        $("#review-" + firstReviewId + "-review").shouldHave(text("review"));
//
//        $("#review-" + secondReviewId + "-camis").shouldHave(text("camis2"));
//        $("#review-" + secondReviewId + "-dba").shouldHave(text("dba2"));
//        $("#review-" + secondReviewId + "-review").shouldHave(text("review2"));
//
//        Stream.of(firstReview, secondReview)
//                .forEach(review -> {
//                    reviewRepository.save(review);
//                });
//
//        // Visit the new review page
//        $("#new-review-link").click();
//
//        // Make sure the link worked and the form is now showing
//        $("#new-review-form").should(appear);
//
//        // Add a new review
//        $("#new-review-camis").sendKeys("third_review");
//        $("#new-review-dba").sendKeys("Third");
//        $("#new-review-review").sendKeys("Review");
//        $("#new-review-submit").click();
//
//        // Make sure we're now on the reviews page again
//        $("#reviews-wrapper").should(appear);
//
//        // Now there should be three Reviews
//        $$("[data-review-display]").shouldHave(size(3));
//
//        refresh();
//
//        // Now there should be three Reviews again after the refresh
//        $$("[data-review-display]").shouldHave(size(3));
//
//        // Check that the data is showing up for the third Review
//        Long thirdReviewId = secondReviewId + 1;
//        $("#review-" + thirdReviewId + "-review-name").shouldHave(text("third_review"));
//        $("#review-" + thirdReviewId + "-first-name").shouldHave(text("Third"));
//        $("#review-" + thirdReviewId + "-last-name").shouldHave(text("Review"));
//
//        // Test Deleting the first review
//        $("#review-" + firstReviewId).should(exist);
//        $$("[data-review-display]").shouldHave(size(3));
//
//        $("#delete-review-" + firstReviewId).click();
//        $("#review-" + firstReviewId).shouldNot(exist);
//
//        $$("[data-review-display]").shouldHave(size(2));
//
//
//        open("http://www.google.com");
//
//        WebElement queryBox = $(By.name("q"));
//        queryBox.sendKeys("Kent Beck");
//        queryBox.submit();
//
//        $("body").shouldHave(text("extreme programming"));
//
//    }
//
//}
