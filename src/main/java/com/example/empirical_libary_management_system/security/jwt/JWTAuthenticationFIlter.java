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
	 String token = null;
	 String userName= null;
	 
     
	 if( authHeader != null && authHeader.startsWith("Bearer ")) {
		 token = authHeader.substring(7); // get rid of "bearer " prefix
		 
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
	catch(Exception e) {
	       response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token validation failed");
           return;
	}
	 
	}
	 else {
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Missing or invalid Authorization header");
         return;
	 }
	 filterChain.doFilter(request, response);
}

}
