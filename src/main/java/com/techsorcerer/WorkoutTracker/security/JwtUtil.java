package com.techsorcerer.WorkoutTracker.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final Key key = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET_KEY.getBytes());
	
	public String generateToken(String userId, String email,String role) {
		return Jwts
				.builder()
				.claim("userId", userId)
				.claim("email", email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_MS))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Claims extractClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
	
	public String extractUserId(String token) {
		return extractClaims(token).get("userId",String.class);
	}
	
	public String extractRole(String token) {
		return extractClaims(token).get("role", String.class);
	}

	
	public boolean isTokenValid(String token) {
		try {
			extractClaims(token);
			return true;
		} catch (JwtException ex) {
			return false;
		}
	}
}
