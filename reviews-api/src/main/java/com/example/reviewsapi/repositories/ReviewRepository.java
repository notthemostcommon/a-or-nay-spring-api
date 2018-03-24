package com.example.reviewsapi.repositories;

import com.example.reviewsapi.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
