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
	public ResponseEntity<ErrorResponse> handleUserServiceExceptions(UserServiceExceptions ex, HttpServletRequest request) {
	    HttpStatus status;

	    String message = ex.getMessage().toLowerCase();

	    if (message.contains("not found")) {
	        status = HttpStatus.NOT_FOUND;
	    } else if (message.contains("unauthorized") || message.contains("access")) {
	        status = HttpStatus.FORBIDDEN;
	    } else {
	        status = HttpStatus.BAD_REQUEST;
	    }

	    ErrorResponse error = new ErrorResponse(
	        status.value(), // int staus
	        status.getReasonPhrase(), // String error
	        ex.getMessage(), // error Message
	        request.getRequestURI() //String path
	    );

	    return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(WorkoutServiceExceptions.class)
	public ResponseEntity<ErrorResponse> handleWorkoutServiceExceptions(WorkoutServiceExceptions ex, HttpServletRequest request) {
	    HttpStatus status;

	    String message = ex.getMessage().toLowerCase();

	    if (message.contains("not found")) {
	        status = HttpStatus.NOT_FOUND;
	    } else if (message.contains("unauthorized") || message.contains("access")) {
	        status = HttpStatus.FORBIDDEN;
	    } else if (message.contains("already exists") || message.contains("duplicate")) {
	        status = HttpStatus.CONFLICT;
	    } else {
	        status = HttpStatus.BAD_REQUEST;
	    }

	    ErrorResponse error = new ErrorResponse(
	        status.value(),
	        status.getReasonPhrase(),
	        ex.getMessage(),
	        request.getRequestURI()
	    );

	    return new ResponseEntity<>(error, status);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN;
		
		ErrorResponse error = new ErrorResponse(
		        status.value(),
		        status.getReasonPhrase(),
		        ex.getMessage(),
		        request.getRequestURI()
		    );
		
		return new ResponseEntity<>(error,status);
		
	}
	@ExceptionHandler(MetaDataException.class)
	public ResponseEntity<ErrorResponse> handleMetaDataException(MetaDataException ex, HttpServletRequest request) {
	    HttpStatus status;

	    String message = ex.getMessage().toLowerCase();

	    if (message.contains("not found")) {
	        status = HttpStatus.NOT_FOUND;
	    } else if (message.contains("already exists") || message.contains("duplicate")) {
	        status = HttpStatus.CONFLICT;
	    } else {
	        status = HttpStatus.BAD_REQUEST;
	    }

	    ErrorResponse error = new ErrorResponse(
	        status.value(),
	        status.getReasonPhrase(),
	        ex.getMessage(),
	        request.getRequestURI()
	    );

	    return new ResponseEntity<>(error, status);
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
