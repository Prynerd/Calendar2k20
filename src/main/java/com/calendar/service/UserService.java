package com.calendar.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.calendar.domain.User;
import com.calendar.requestdto.RegistrationDto;

public interface UserService extends UserDetailsService{
	
	public void createUser(RegistrationDto regDto);
	
	public String validationTokenGeneration();
	
	public void deleteUser(int id);
	
	public User getFullUser();
}
