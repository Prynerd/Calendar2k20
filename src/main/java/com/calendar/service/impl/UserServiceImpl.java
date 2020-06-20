package com.calendar.service.impl;

import java.security.SecureRandom;

import javax.transaction.Transactional;

import com.calendar.exceptions.UserNotLoggedInException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.calendar.domain.User;
import com.calendar.exceptions.EmailAlreadyExistsException;
import com.calendar.exceptions.UserDeletedException;
import com.calendar.repository.UserRepository;
import com.calendar.repository.custom.CustomUserRepository;
import com.calendar.requestdto.RegistrationDto;
import com.calendar.responsedto.UserResponseDto;
import com.calendar.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CustomUserRepository customUserRepository;
	private PasswordEncoder passwordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.customUserRepository = customUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(()
				-> new UsernameNotFoundException("User not found"));
		if (user.isDeleted() == false) {
			return user;
		} else {
			throw new UserDeletedException("accountDeleted");
		}
	}

	@Override
	@Transactional
	public void createUser(RegistrationDto regDto) {
		if (userRepository.existsByEmail(regDto.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		User user = new User(
				regDto.getEmail(),
				passwordEncoder.encode(regDto.getPassword()),
				validationTokenGeneration());

		userRepository.save(user);
	}

	@Override
	public String validationTokenGeneration() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}

	@Override
	public User getFullUser() {
		User user;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (ClassCastException e) {
			throw new UserNotLoggedInException("You are not logged in!");
		}
		return user;
	}

	@Override
	public UserResponseDto getUser() {
		User user = getFullUser();
		UserResponseDto urDto = new UserResponseDto(user.getId(), user.getEmail(), user.isValidated());

		return urDto;
	}

	@Override
	@Transactional
	public void setProjectsVisibilityStatus(boolean status) {
		
		User user = getFullUser();
		user.setOnlyActiveProjects(status);
		
		customUserRepository.setOnlyActiveProjectById(user.getId(), status);
	}


}
