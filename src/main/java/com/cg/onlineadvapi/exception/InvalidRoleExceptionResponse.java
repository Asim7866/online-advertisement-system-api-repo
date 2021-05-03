package com.cg.onlineadvapi.exception;

public class InvalidRoleExceptionResponse {
	
	String message;

	public InvalidRoleExceptionResponse() {
		super();
	}

	public InvalidRoleExceptionResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
