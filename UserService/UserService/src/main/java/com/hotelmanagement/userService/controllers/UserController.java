package com.hotelmanagement.userService.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmanagement.userService.entities.User;
import com.hotelmanagement.userService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	int retryCount = 1;
	
	// single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		logger.info("Get Single User Handler: Usercontroller");
		logger.info("Retry count: {}", retryCount);
		retryCount++;
		User existingUser = userService.getUser(userId);
		return ResponseEntity.ok(existingUser);
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//		logger.info("Fallback is exicuted because service is down: ", ex.getMessage());
		User user = User.builder()
		.email("dummy@email.com")
		.name("Dummy")
		.about("This is a dummy user created because some service is down.")
		.userId("12345")
		.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
}
