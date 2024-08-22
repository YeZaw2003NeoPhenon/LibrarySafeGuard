package com.example.empirical_libary_management_system.user;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class apiResponse<T>{

	private HttpStatus status;
	private String message;
	
	private T data;
	
    public apiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
