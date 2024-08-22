package com.example.empirical_libary_management_system.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	    public Map<String, String> userNotFound(UserNotFoundException ex ){
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return error;
	    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidation(MethodArgumentNotValidException exception){
		Map<String, Object> errors = new HashMap<>();
		errors.put("success", false);
		errors.put("errors",
				exception.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						FieldError::getField,
						FieldError::getDefaultMessage)
						));
		
		return errors;
	}
	 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public Map<String , Object> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception){
		
		Map<String ,Object> errors = new HashMap<>();
		 errors.put("error", exception.getMessage());
		return errors;
	}
	
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(BookNotFoundException.class)
	    public Map<String, String> bookNotFound(BookNotFoundException ex){
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return error;
	    }
	    
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(BookAlreadyExistException.class)
	    public Map<String, String> bookAlreadyExist(BookAlreadyExistException ex){
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return error;
	    }
	    @ResponseStatus(HttpStatus.CONFLICT)
	    @ExceptionHandler(InvalidCreadentialException.class)
	    public Map<String, String> invalidCredentials(InvalidCreadentialException ex){
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return error;
	    }
	    
	    @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Access denied: You do not have the necessary permissions.");
	        response.put("error", ex.getMessage());
	        response.put("status", HttpStatus.FORBIDDEN.value());
	        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	    }

	    @ExceptionHandler(AuthenticationException.class)
	    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Authentication failed: Invalid credentials.");
	        response.put("error", ex.getMessage());
	        response.put("status", HttpStatus.UNAUTHORIZED.value());
	        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	    }

}
