package com.example.empirical_libary_management_system.exception;

public class UserAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
