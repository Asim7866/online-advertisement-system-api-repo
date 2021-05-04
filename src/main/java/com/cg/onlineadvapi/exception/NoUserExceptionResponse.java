package com.cg.onlineadvapi.exception;

/**
 * This Exception response class is responsible for handling Exception regarding
 * no messages.
 * 
 * @author mohdansa
 */
public class NoUserExceptionResponse {

	/**
	 * This field is used to get error message regarding no messages.
	 */
	private String user;

	/**
	 * Constructor with parameters.
	 * 
	 * @param message
	 */
	public NoUserExceptionResponse(String user) {
		super();
		this.user = user;
	}

	/**
	 * Getters and Setters.
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
