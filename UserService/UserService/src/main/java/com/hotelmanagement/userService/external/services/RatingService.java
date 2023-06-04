package com.hotelmanagement.userService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.hotelmanagement.userService.entities.Ratings;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    //get

    //POST

    @PostMapping("/ratings")
    public ResponseEntity<Ratings> createRating(Ratings values);


    //PUT
    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Ratings> updateRating(@PathVariable("ratingId") String ratingId, Ratings rating);


    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
