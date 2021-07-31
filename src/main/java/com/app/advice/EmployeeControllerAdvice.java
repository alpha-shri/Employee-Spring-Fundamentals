package com.app.advice;

import java.util.NoSuchElementException;

import com.app.exceptions.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.exceptions.EmptyInputException;

@ControllerAdvice
@ResponseStatus
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler{

// CustomException handling
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<ErrorMessage> handleEmptyInput(EmptyInputException emptyInputException){

		ErrorMessage msg = new ErrorMessage(HttpStatus.BAD_REQUEST, "Input field cannot be blank");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(msg);
	}

// Pre-defined Exception class handling
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElement(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No value is present in DB. Please change your employee ID", HttpStatus.NOT_FOUND);
	}

// To handle HTTP-Method type mismatch.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		return new ResponseEntity<Object>("Please change HTTP method type", HttpStatus.NOT_FOUND);
	}
	
}
