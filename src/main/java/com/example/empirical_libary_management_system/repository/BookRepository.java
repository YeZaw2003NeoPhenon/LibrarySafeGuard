package com.example.empirical_libary_management_system.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.empirical_libary_management_system.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	public abstract List<Book> findByTitle(String title);
	 boolean existsByIsbn(UUID isbn);
}

