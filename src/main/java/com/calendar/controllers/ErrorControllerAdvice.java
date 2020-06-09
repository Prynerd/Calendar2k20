package com.calendar.controllers;

import com.calendar.exceptions.UserNotLoggedInException;
import com.calendar.responsedto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(UserNotLoggedInException.class)
    public ResponseEntity<ApiError> handleUserNotLoggedIn(UserNotLoggedInException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage()));
    }
}
