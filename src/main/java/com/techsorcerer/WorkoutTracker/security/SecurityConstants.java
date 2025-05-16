package com.techsorcerer.WorkoutTracker.security;

public class SecurityConstants {

	public static final String JWT_SECRET_KEY = "mayanksupersecurekeyforworkoutapp12345"; // Min 32 chars for HMAC
	public static final long JWT_EXPIRATION_MS = 1000 * 60 * 60 * 10; // 10 hours
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	private SecurityConstants() {
		// prvent instantiation
	}

}
