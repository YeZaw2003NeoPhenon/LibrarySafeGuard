package com.example.empirical_libary_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.empirical_libary_management_system.dto.BookDto;
import com.example.empirical_libary_management_system.service.BookServiceImp;
import com.example.empirical_libary_management_system.user.apiResponse;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private final BookServiceImp bookServiceImp;
	
	public BookController(BookServiceImp bookServiceImp) {
		this.bookServiceImp = bookServiceImp;
	}
	
	@RequestMapping( method = RequestMethod.GET , value = "/")
	public ResponseEntity<apiResponse<List<BookDto>>> getAllBooks(){
		List<BookDto> books = bookServiceImp.getAllBooks();
		apiResponse<List<BookDto>> response = new apiResponse<>(HttpStatus.OK, "Books retrieved successfully", books);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	
	@RequestMapping( method = RequestMethod.GET , value = "select/{id}")
    public ResponseEntity<apiResponse<BookDto>> getBookById(@PathVariable Long id) {
		
        return bookServiceImp.getBookById(id)
                .map(book -> new ResponseEntity<>(new apiResponse<BookDto>(HttpStatus.OK, "Book Truimphantly Retrived!", book) , HttpStatus.OK))
                .orElse( new ResponseEntity<>(new apiResponse<BookDto>(HttpStatus.NOT_FOUND , "Not way to summon up your attempting book!" , null ),HttpStatus.NOT_FOUND));
    } 
	
	@RequestMapping( method = RequestMethod.POST , value = "/create")
    public ResponseEntity<apiResponse<BookDto>> addBook(@RequestBody BookDto bookDTO) {
		BookDto savedBook = bookServiceImp.insertBook(bookDTO);
		apiResponse<BookDto> response = new apiResponse<>(HttpStatus.CREATED, "Books retrieved successfully", savedBook);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	@RequestMapping( method = RequestMethod.PUT , value = "/update/{id}")
    public ResponseEntity<apiResponse<Boolean>> updateBook(@PathVariable Long id, @RequestBody BookDto bookDTO) {
        boolean updated = bookServiceImp.updateBook(bookDTO, id);
        if (updated) {
        	apiResponse<Boolean> response = new apiResponse<>(HttpStatus.OK, "Book updated umabominably!", true);
        	return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
        	apiResponse<Boolean> response = new apiResponse<>(HttpStatus.NOT_FOUND, "Book not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping( method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<apiResponse<Void>> deleteBook(@PathVariable Long id) {
        apiResponse<Void> response = new apiResponse<>(HttpStatus.NO_CONTENT, "Book deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
	
}
