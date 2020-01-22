package com.calendar.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private LocalDateTime registrationTime;

	private String validateToken;

	private boolean isValidated;

	private boolean isDeleted;

	public User() {

	}

	public User(String email, String password, String validateToken) {
		this.email = email;
		this.password = password;
		this.validateToken = validateToken;

		this.registrationTime = LocalDateTime.now();
		
		isValidated = false;
		isDeleted = false;
	}

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public String getValidateToken() {
		return validateToken;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isDeleted;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isDeleted;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isDeleted;
	}

	@Override
	public boolean isEnabled() {
		return !isDeleted;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
