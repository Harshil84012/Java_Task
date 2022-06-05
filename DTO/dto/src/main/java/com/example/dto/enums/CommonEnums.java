package com.example.dto.enums;




public enum CommonEnums {
	
	EXCEPTION_MSG("student not found");
	private String message;
	
	CommonEnums(String message){
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message=message;
	}
}
