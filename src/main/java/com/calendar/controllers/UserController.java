package com.calendar.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.requestdto.RegistrationDto;
import com.calendar.responsedto.UserResponseDto;
import com.calendar.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/registration")
	public void registration(@Valid @RequestBody RegistrationDto regDto) {
		userService.createUser(regDto);
	}
	
	@GetMapping("/user")
    public UserResponseDto isLogged(){
        
        UserResponseDto user = userService.getUser();
        return user;
    }
	
	
}
