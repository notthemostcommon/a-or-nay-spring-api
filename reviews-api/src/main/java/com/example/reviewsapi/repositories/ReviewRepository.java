package com.example.reviewsapi.repositories;

import com.example.reviewsapi.models.Review;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
