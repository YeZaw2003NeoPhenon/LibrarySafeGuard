package com.example.empirical_libary_management_system.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
	
	   private Long id;
	   private String title;
	   private String author;
	   private String edition;
	   private double prices;
	   private UUID isbn = UUID.randomUUID();
}
