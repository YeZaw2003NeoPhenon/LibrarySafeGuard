package com.example.empirical_libary_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.empirical_libary_management_system.repository.BookRepository;

@SpringBootApplication
public class EmpiricalLibaryManagementSystemApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(EmpiricalLibaryManagementSystemApplication.class, args);
	}
}
