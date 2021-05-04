package com.cg.onlineadvapi.exception;

public class PasswordMismatchExceptionResponse {

	String message;

	public PasswordMismatchExceptionResponse() {
		super();
	}

	public PasswordMismatchExceptionResponse(String message) {
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
