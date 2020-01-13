package com.calendar.service.impl;

import java.security.SecureRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.calendar.domain.User;
import com.calendar.exceptions.EmailAlreadyExistsException;
import com.calendar.exceptions.UserDeletedException;
import com.calendar.repository.UserRepository;
import com.calendar.requestdto.RegistrationDto;
import com.calendar.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
	public String validationTokenGeneration() {
		SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
	}

	@Override
	public User getFullUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}
}
