package com.hotelmanagement.userService.serviceImplementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelmanagement.userService.entities.Hotel;
import com.hotelmanagement.userService.entities.Ratings;
import com.hotelmanagement.userService.entities.User;
import com.hotelmanagement.userService.exceptions.ResourceNotFoundException;
import com.hotelmanagement.userService.external.services.HotelService;
import com.hotelmanagement.userService.repositories.UserRepository;
import com.hotelmanagement.userService.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with id: "+userId));
		
		// fetch rating of the above user from rating service
		// http://localhost:8813/rating/user/f87a7c6a-98fc-44d9-b268-fbba9f61836e
		
		Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+existingUser.getUserId(), Ratings[].class);
		logger.info("{} ", ratingsOfUser);
		List<Ratings> ratings = Arrays.stream(ratingsOfUser).toList();
		
		
		List<Ratings> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8812/hotel/bf65a164-5fa3-4923-ad13-64a40ef11a68
			// ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// logger.info("response status code: {} ",forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		existingUser.setRatings(ratingList);
		
		return existingUser;
	}

}
