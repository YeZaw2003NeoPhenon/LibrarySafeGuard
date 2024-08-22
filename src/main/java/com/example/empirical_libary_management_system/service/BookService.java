package com.example.empirical_libary_management_system.service;

import java.util.List;
import java.util.Optional;
import com.example.empirical_libary_management_system.dto.BookDto;

public interface BookService {
	
	public abstract List<BookDto> getAllBooks();
	public abstract BookDto insertBook(BookDto bookDto);
	public abstract Optional<BookDto> getBookById(Long id);
	public abstract boolean updateBook(BookDto bookDto , Long id);
	public abstract void deleteBook(Long id);
}
