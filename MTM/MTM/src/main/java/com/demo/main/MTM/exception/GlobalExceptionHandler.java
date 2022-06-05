package com.demo.main.MTM.exception;

import java.util.Date;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {

		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {

		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> handleAPIException(APIException exception, WebRequest request) {

		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			WebRequest request) {

		ErrorDetail errorDetails = new ErrorDetail(new Date(), "VALIDATION ERROR",
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);

	}
}
