package com.example.empirical_libary_management_system.security.jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class JWTController {
	
	
	private final JWTService jwtService;
	
	
	private final AuthenticationManager authenticationManager;
	
	@RequestMapping(method = RequestMethod.POST)
	public String getTokenForAuthenticatedUser( @RequestBody JWTAuthRequest authRequest) {
		
	Authentication authentication =	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
	
	 if( authentication.isAuthenticated()) {
		 return jwtService.getGeneratedToken(authRequest.getUserName()); 
	 }
	 else {
		 throw new UsernameNotFoundException("User auth is wholeheartedly invalid to pore over!");
	 }
	}
}
