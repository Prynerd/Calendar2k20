package com.calendar.repository.custom;

public interface CustomUserRepository {

	void setOnlyActiveProjectById(int id, boolean status);
	
}
