package com.calendar.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
