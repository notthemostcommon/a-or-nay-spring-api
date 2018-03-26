package com.example.reviewsapi.repositories;

import com.example.reviewsapi.models.Review;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository reviewRepository;

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

        entityManager.persist(firstReview);
        entityManager.persist(secondReview);
        entityManager.flush();
    };

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<Review> reviewsFromDb = reviewRepository.findAll();

        assertThat(Iterables.size(reviewsFromDb), is(2));
    }

    @Test
    public void findAll_returnsUserId() {
        Iterable<Review> reviewsFromDb = reviewRepository.findAll();

        Long secondReviewsUserId = Iterables.get(reviewsFromDb, 1).getUser_id();

        assertThat(secondReviewsUserId, is(12344444));
    }

    @Test
    public void findAll_returnsFirstName() {
        Iterable<Review> reviewsFromDb = reviewRepository.findAll();

        String secondReviewsCamis = Iterables.get(reviewsFromDb, 1).getCamis();

        assertThat(secondReviewsCamis, is("camis2"));
    }

    @Test
    public void findAll_returnsDba() {
        Iterable<Review> reviewsFromDb = reviewRepository.findAll();

        String secondUsersLastName = Iterables.get(reviewsFromDb, 1).getDba();

        assertThat(secondUsersLastName, is("dba2"));
    }
}
