package com.example.empirical_libary_management_system.security.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.empirical_libary_management_system.security.LibaryServiceImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFIlter extends OncePerRequestFilter{
	
	private final JWTService jwtService;
	
	private final LibaryServiceImp libaryServiceImp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	 String authHeader	= request.getHeader(HttpHeaders.AUTHORIZATION); 
	 String userName= null;
	 
     
	 if( authHeader == null && !authHeader.startsWith("Bearer ")) {
		 jwtConfig.getAuthHeader()
		 return;
	 }
		 String token = authHeader.replace("Bearer " , "");
		
	  try {
		userName = jwtService.extractUserNameFromToken(token);
		
		 if( userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
				UserDetails userDetails =  libaryServiceImp.loadUserByUsername(userName);
				if( jwtService.validateToken(token, userDetails)) {
					
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			 }
	}
	catch(JwtException e) {
	       response.sendError(String.format("Unauthorized: Token %s validation failed"+token));
    }
	  filterChain.doFilter(request, response);
	}
}
