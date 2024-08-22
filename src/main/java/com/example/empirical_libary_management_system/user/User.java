package com.example.empirical_libary_management_system.user;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users2")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id" , nullable = false , updatable = false)
	private Long id;
	
	@NotBlank(message = "First Name must not be empty , Pls pore over on what you write!")
	@Column(name = "first_name")
	private String firstname;
	
	@NotBlank(message = "Last Name must not be empty , Pls pore over on what you write!")
	@Column(name = "last_name")
	private String lastname;
	
	@NotBlank(message = "Email must not be empty!")
	@Email(message = "Make sure you are on right track while dealing with email format!")
	@Column(name = "email")
	@NaturalId(mutable = true)
	private String email;
	
	
	@NotBlank(message = "Password must not be empty!")
	@Column(name = "password")
	private String password;
	
	
	@NotBlank(message = "Roles must not be empty!")
	@Column(name = "roles")
	private String roles;
	
	@CreationTimestamp
	@Column(name = "created_at" , updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
