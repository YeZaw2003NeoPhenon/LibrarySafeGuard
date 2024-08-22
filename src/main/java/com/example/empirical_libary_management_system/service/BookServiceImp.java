package com.example.empirical_libary_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empirical_libary_management_system.dto.BookConverter;
import com.example.empirical_libary_management_system.dto.BookDto;
import com.example.empirical_libary_management_system.entity.Book;
import com.example.empirical_libary_management_system.exception.BookAlreadyExistException;
import com.example.empirical_libary_management_system.exception.BookNotFoundException;
import com.example.empirical_libary_management_system.repository.BookRepository;

@Service
public class BookServiceImp implements BookService{
	
    private final BookRepository bookRepository;
    
    private final BookConverter bookConverter;

    @Autowired
    public BookServiceImp(BookRepository bookRepository , BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }
    
	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books =  bookRepository.findAll();
		return books.stream().map(bookConverter::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public BookDto insertBook(BookDto bookDto) {
		Book book = bookConverter.convertToEntity(bookDto);
		  if (bookRepository.existsByIsbn(book.getIsbn())) {
		        throw new BookAlreadyExistException("The book with this ISBN already exists!");
		    }
		
		Book amplyfiedBook =  bookRepository.save(book);
		return bookConverter.convertToDTO(amplyfiedBook);
	}
	
	@Override
	public Optional<BookDto> getBookById(Long id) {
		return Optional.ofNullable(bookRepository.findById(id).map(bookConverter::convertToDTO).orElseThrow(() -> new BookNotFoundException("Book with particularly given ID:"+id+"not trackable!")));
	}

	@Override
	public boolean updateBook(BookDto bookDto, Long id) {
			
//		 if( bookRepository.existsById(id)) {
//			 bookRepository.save(book);
//			 return true;
//		 }
//		 else {
//			 return false;
//		 }
		
		if( !bookRepository.existsById(id)) {
			throw new BookNotFoundException("Book with particularly given ID:"+id+"not trackable!");
		}
		
	    return bookRepository.findById(id).map(existingBook -> {
	        existingBook.setTitle(bookDto.getTitle());
	        existingBook.setAuthor(bookDto.getAuthor());
	        existingBook.setEdition(bookDto.getEdition());
	        existingBook.setIsbn(bookDto.getIsbn());
	        existingBook.setPrices(bookDto.getPrices());
	        bookRepository.save(existingBook);
	        return true; 
	    }).orElse(false);
	}
	
	@Override
	public void deleteBook(Long id) {
	 Optional<Book> op	= bookRepository.findById(id);
	  op.ifPresentOrElse(book -> bookRepository.deleteById(book.getId()), () -> new BookNotFoundException("Book with particularly given ID:"+id+"not trackable!"));
	}
	
}
