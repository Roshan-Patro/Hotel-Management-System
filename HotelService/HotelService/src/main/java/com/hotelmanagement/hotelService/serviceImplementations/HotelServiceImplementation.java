package com.hotelmanagement.hotelService.serviceImplementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagement.hotelService.entities.Hotel;
import com.hotelmanagement.hotelService.exceptions.ResourceNotFoundException;
import com.hotelmanagement.hotelService.repositories.HotelRepository;
import com.hotelmanagement.hotelService.services.HotelService;

@Service
public class HotelServiceImplementation implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getSingleHotel(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found on server with id: "+hotelId));
	}

}
