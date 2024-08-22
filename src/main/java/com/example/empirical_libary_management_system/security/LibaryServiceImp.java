package com.example.empirical_libary_management_system.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.empirical_libary_management_system.user.UserRepository;

@Component
public class LibaryServiceImp implements UserDetailsService{

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	return userRepository.findByEmail(username)
			   .map(LibarySecurity::new)
			   .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
//		return new LibarySecurity(user);
	}
	
}
