package com.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendar.domain.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{

	
}
