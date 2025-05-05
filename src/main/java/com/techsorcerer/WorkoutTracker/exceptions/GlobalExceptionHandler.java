package com.techsorcerer.WorkoutTracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techsorcerer.WorkoutTracker.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserServiceExceptions.class)
	public ResponseEntity<ErrorResponse> handleUserServiceExcetions(UserServiceExceptions ex, HttpServletRequest request){
		ErrorResponse error = new ErrorResponse(
		        HttpStatus.CONFLICT.value(),
		        HttpStatus.CONFLICT.getReasonPhrase(),
		        ex.getMessage(),
		        request.getRequestURI()
		    );
		    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
		  ErrorResponse error = new ErrorResponse(
			        HttpStatus.INTERNAL_SERVER_ERROR.value(),
			        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
			        ex.getMessage(),
			        request.getRequestURI()
			    );
			    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);	
    }

}
