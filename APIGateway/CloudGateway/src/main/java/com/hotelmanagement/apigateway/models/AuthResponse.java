package com.hotelmanagement.apigateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expiresAt;
	private Collection<String> authorities;

}
