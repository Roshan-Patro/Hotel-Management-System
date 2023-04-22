package com.hotelmanagement.hotelService.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@GetMapping
	public ResponseEntity<List<String>> getStaffs(){
		 List<String> staffs = Arrays.asList("Pramod Pal", "Vinod Singh", "Aakash Chimnani");
		 
		 return new ResponseEntity<List<String>>(staffs, HttpStatus.OK);
	}
}
