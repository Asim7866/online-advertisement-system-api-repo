package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception Class is responsible for Handling Exception regarding Same
 * Sender.
 * 
 * @author mohdansa
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoUserException extends RuntimeException {

	/**
	 * The serialVersionUID ensure that a loaded class corresponds exactly to a
	 * Serialized object.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create SenderMessageException object without Error Message.
	 */
	public NoUserException() {
		super();
	}

	/**
	 * Create SenderMessageException object with Error Message.
	 */
	public NoUserException(String errMsg) {
		super(errMsg);
	}
}
