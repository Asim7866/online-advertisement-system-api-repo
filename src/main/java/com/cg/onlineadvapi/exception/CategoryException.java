package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryException() {
		super();
	}

	public CategoryException(String str) {
		super(str);
	}
}
