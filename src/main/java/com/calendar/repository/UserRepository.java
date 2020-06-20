package com.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendar.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail (String email);

	boolean existsByEmail(String email);
}
