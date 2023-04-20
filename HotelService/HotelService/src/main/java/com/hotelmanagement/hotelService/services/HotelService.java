package com.hotelmanagement.hotelService.services;

import java.util.List;

import com.hotelmanagement.hotelService.entities.Hotel;

public interface HotelService {
	Hotel create(Hotel hotel);
	
	List<Hotel> getAllHotel();
	
	Hotel getSingleHotel(String hotelId);
}
