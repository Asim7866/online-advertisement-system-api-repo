package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Create NullUserException object without error message
	 */
	public NullUserException() {
		super();
	}

	/**
	 * Create NullUserException object with error message
	 */
	public NullUserException(String errMsg) {
		super(errMsg);
	}
}