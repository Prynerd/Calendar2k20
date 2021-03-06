package com.calendar.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.calendar.domain.User;
import com.calendar.requestdto.RegistrationDto;
import com.calendar.responsedto.UserResponseDto;

public interface UserService extends UserDetailsService{
	
	public void createUser(RegistrationDto regDto);
	
	public String validationTokenGeneration();
	
	public User getFullUser();
	
	public UserResponseDto getUser();
	
	public void setProjectsVisibilityStatus(boolean status);
}
