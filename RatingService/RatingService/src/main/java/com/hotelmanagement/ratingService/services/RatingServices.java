package com.hotelmanagement.ratingService.services;

import java.util.List;

import com.hotelmanagement.ratingService.entities.Rating;

public interface RatingServices {
	Rating createRating(Rating rating);
	
	Rating getRatingById(String ratingId);
	
	List<Rating> getAllByUserId(String userId);
	
	List<Rating> getAllByHotelId(String hotelId);
	
	List<Rating> getAll();
}
