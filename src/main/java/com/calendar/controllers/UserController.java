package com.calendar.controllers;

import javax.validation.Valid;

import com.calendar.exceptions.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<UserResponseDto> isLogged() {
		UserResponseDto user;
		user = userService.getUser();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/userSettings/onlyActiveProjects{status}")
	public void setProjectsVisibilityStatus(@RequestParam boolean status) {
		userService.setProjectsVisibilityStatus(status);
	}

}
