package com.calendar.exceptions;

public class EntryNotFoundException extends RuntimeException{

    public EntryNotFoundException(String message) {
        super(message);
    }
}
