package com.example.empirical_libary_management_system.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class JWTService {
		
	@Value("${spring.jwt.JwtExpirationTime}")
	private int JWT_EXPIRATION_TIME;
	
	@Value("${spring.jwt.secret}")
	private String JWT_SECRET_KEY;
	
	public String getGeneratedToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return tokenGenerator(claims , userName);
	}
	
	private String tokenGenerator(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(userName)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				   .signWith(getKeySign() , SignatureAlgorithm.HS256)
				   .compact();
	}
	
	private Key getKeySign() {
		byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String extractUserNameFromToken(String theToken) {
		return extractClaim(theToken , Claims::getSubject);
	}
	
	public Date extractExpirationTimeFromToken(String theToken) {
		return extractClaim(theToken , Claims::getExpiration);
	}

	  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	 }
	  
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getKeySign())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	

	private boolean IsTokenExpired(String theToken) {
		return extractExpirationTimeFromToken(theToken).before(new Date());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserNameFromToken(token);
		return userDetails.getUsername().equals(userName) && !IsTokenExpired(token);
	}
	
}
