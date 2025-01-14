package com.example.empirical_libary_management_system.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.empirical_libary_management_system.user.User;

import lombok.Data;

@Data
public class LibarySecurity implements UserDetails {
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public LibarySecurity(User user) {
		this.username=	user.getEmail();
		this.password = user.getPassword();
		this.authorities = recuperatedRolesAmplified(user.getRoles());
	}

	public List<GrantedAuthority> recuperatedRolesAmplified(String roles) {
		return Arrays.stream(roles.split(","))
		        .map(SimpleGrantedAuthority::new)
		        .collect(Collectors.toList());
		}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	 public boolean isEnabled() {
	        return true;
	 }
	
}
