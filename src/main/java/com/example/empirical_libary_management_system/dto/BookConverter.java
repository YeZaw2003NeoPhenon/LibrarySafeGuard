package com.example.empirical_libary_management_system.dto;

import org.springframework.stereotype.Component;

import com.example.empirical_libary_management_system.entity.Book;

@Component
public class BookConverter {
	
	  public BookDto convertToDTO(Book book) {
	        BookDto dto = new BookDto();
	        dto.setId(book.getId());
	        dto.setTitle(book.getTitle());
	        dto.setAuthor(book.getAuthor());
	        dto.setEdition(book.getEdition());
	        dto.setPrices(book.getPrices());
	        dto.setIsbn(book.getIsbn());
	        return dto;
	    }

	    public Book convertToEntity(BookDto dto) {
	        Book book = new Book();
	        book.setId(dto.getId());
	        book.setTitle(dto.getTitle());
	        book.setAuthor(dto.getAuthor());
	        book.setEdition(dto.getEdition());
	        dto.setPrices(book.getPrices());
	        book.setIsbn(dto.getIsbn());
	        return book;
	    }
}
