package com.calendar.exceptions;

public class UserDeletedException extends RuntimeException{

	public UserDeletedException(String message) {
        super(message);
    }
}
