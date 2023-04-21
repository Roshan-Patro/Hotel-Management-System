package com.hotelmanagement.ratingService.serviceImplementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.ratingService.entities.Rating;
import com.hotelmanagement.ratingService.exceptions.ResourceNotFoundException;
import com.hotelmanagement.ratingService.repository.RatingRepository;
import com.hotelmanagement.ratingService.services.RatingServices;

@Service
public class RatingServiceImplamentation implements RatingServices {
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepository.save(rating);
	}

	@Override
	public Rating getRatingById(String ratingId) {
		return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Resource not found on server with id: "+ratingId));
	}

	@Override
	public List<Rating> getAllByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

	@Override
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

}
