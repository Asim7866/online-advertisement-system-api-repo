package com.cg.onlineadvapi.exception;

public class FieldCannotBeBlankExceptionResponse {
	private String error;

	public FieldCannotBeBlankExceptionResponse(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
