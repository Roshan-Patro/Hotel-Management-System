package com.hotelmanagement.userService.services;

import java.util.List;

import com.hotelmanagement.userService.entities.User;

public interface UserService {
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
	
	// Delete
	
	// Update
}
