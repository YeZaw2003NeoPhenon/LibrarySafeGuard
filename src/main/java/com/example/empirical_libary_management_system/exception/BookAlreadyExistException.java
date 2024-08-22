package com.example.empirical_libary_management_system.exception;


public class BookAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BookAlreadyExistException(String message) {
		super(message); 
	}
	
}
