package com.demo.main.security.exception;

public class APIException extends RuntimeException{
	
	private static final long serialVersionUID=1L;
	private APIException(String message){
		super(message);
	}

}
