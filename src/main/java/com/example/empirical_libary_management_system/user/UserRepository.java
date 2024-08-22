package com.example.empirical_libary_management_system.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User , Long>{
	
	public abstract Optional<User> findByEmail(String email);
	public abstract void deleteByEmail(String email);
}
