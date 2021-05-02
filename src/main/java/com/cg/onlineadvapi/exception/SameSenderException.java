package com.cg.onlineadvapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * This Exception class is responsible for handling exception regarding sender id 
 * @author mohdansa
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SameSenderException extends RuntimeException{

	/**
	 * The serialVersionUID ensure that a loaded class corresponds exactly to a Serialized object.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create SenderNameException object without error message
	 */
	public SameSenderException() {
		super();
	}
	/**
	 * Create SenderNameException object with error message
	 */
	public SameSenderException(String errMsg) {
		super(errMsg);
	}
}
