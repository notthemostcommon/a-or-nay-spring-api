package com.example.aornayapi.repositories;

import com.example.aornayapi.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
