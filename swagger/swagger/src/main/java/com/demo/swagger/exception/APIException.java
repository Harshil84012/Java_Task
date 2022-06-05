package com.demo.swagger.exception;

public class APIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private APIException(String message) {
		super(message);
	}

}
