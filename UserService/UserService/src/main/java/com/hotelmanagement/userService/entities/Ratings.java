package com.hotelmanagement.userService.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ratings {
	private String ratingId;
	private String userId;
	private String hotelId;
	private Integer rating;
	private String feedback;
	private Hotel hotel;
}
