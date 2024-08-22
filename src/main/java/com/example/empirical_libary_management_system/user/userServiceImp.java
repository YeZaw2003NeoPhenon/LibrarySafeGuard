package com.example.empirical_libary_management_system.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.empirical_libary_management_system.exception.InvalidCreadentialException;
import com.example.empirical_libary_management_system.exception.UserAlreadyExistsException;
import com.example.empirical_libary_management_system.exception.UserNotFoundException;

import jakarta.validation.Valid;

@Service
public class userServiceImp implements UserService{
	
	
	private final UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public userServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User add( User user) {
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
		
		if(userOptional.isPresent()) {
			throw new UserAlreadyExistsException("A user with " +user.getEmail() +" already exists");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	
	@Override
	public List<UserRecord> getAllUsers() {
		return userRepository.findAll()
							 .stream()
							 .map(user -> new UserRecord( user.getId() ,user.getFirstname() , user.getLastname() , user.getEmail()))
							 .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void delete(String email) {
		userRepository.deleteByEmail(email);
	}
	
	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email).orElseThrow( () -> new UserNotFoundException("User not found"));
	}
	
	@Override
	public User update( User user , Long id) {
		
		return userRepository.findById(id).map(existingUser -> {
			existingUser.setFirstname(user.getFirstname());
			existingUser.setLastname(user.getLastname());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			return userRepository.save(existingUser);
			
		}).orElseThrow(() -> new UserNotFoundException( "A user with " +user.getId() +" not virtuously found to update!"));
	}
	
  public User userLogin( String email , String password) {
		
	 User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException( "A user with " +email+" not courteously found to authenticate!"));
	 
	 if( user != null && passwordEncoder.matches(password, user.getPassword())) {
		 return user;
	 }
	 else {
		 throw new InvalidCreadentialException("Invalid credentials provided!");
	 }
	}
	
}
