package com.example.empirical_libary_management_system.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.empirical_libary_management_system.security.LibarySecurity;
import com.example.empirical_libary_management_system.user.User;
import com.example.empirical_libary_management_system.user.userServiceImp;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private userServiceImp userServiceImp;
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST )
	public ResponseEntity<Object> login( @RequestBody Map<String, String> loginRequest ){
	try {
		String email = loginRequest.get("email");
		String password = loginRequest.get("password");
		
		User authenticatedUser = userServiceImp.userLogin(email, password);
		
		LibarySecurity libarySecurity = new LibarySecurity(authenticatedUser);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(libarySecurity, null , libarySecurity.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		
	 Map<String, Object> response = new HashMap<>();
	 response.put("message", "Loggin Authentication Untarnishedly Successful!");
	 response.put("Authenticated credential", authenticatedUser);
	 response.put("success", true);
	 response.put("status", HttpStatus.OK.value());
	 return new ResponseEntity<>(response , HttpStatus.OK);
	}
	catch (Exception e) {
		 Map<String, Object> response = new HashMap<>();
		 response.put("message", "Loggin Authentication Untarnishedly Successful!");
		 response.put("success", false);
		 response.put("status", HttpStatus.UNAUTHORIZED.value());
		 return new ResponseEntity<>(response , HttpStatus.UNAUTHORIZED);
	}
 }
	
	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public ResponseEntity<Object> logout(){
		
		SecurityContextHolder.clearContext();
		 Map<String, Object> response = new HashMap<>();
		 response.put("message", "Logout Triumphant!");
		 response.put("success", true);
		 response.put("status", HttpStatus.OK.value());

		 return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
}
