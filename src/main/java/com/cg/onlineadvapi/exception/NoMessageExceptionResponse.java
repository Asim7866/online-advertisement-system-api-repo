package com.cg.onlineadvapi.exception;
/**
 * This Exception response class is responsible for handling Exception regarding no messages.
 * @author mohdansa
 */
public class NoMessageExceptionResponse {

	/**
	 * This field is used to get error message regarding no messages.
	 */
	private String message;

	
	/**
	 * Constructor with parameters.
	 * @param message
	 */
	public NoMessageExceptionResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * Getters and Setters.
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
