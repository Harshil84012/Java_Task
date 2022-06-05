package com.demo.main.exception;

public class APIException extends RuntimeException{
	
	private static final long serialVersionUID=1L;
	APIException(String message){
		super(message);
	}

}
