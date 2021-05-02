package com.cg.onlineadvapi.exception;
/**
 * This Exception response class is responsible for handling exception regarding no messages for a user.
 * @author mohdansa
 */
public class SameSenderExceptionResponse {

	/**
	 * This field is used to get error message regarding no messages for user.
	 */
	private String senderMessages;

	/**
	 * Constructor with parameters.
	 * @param message
	 */
	public SameSenderExceptionResponse(String senderMessages) {
		super();
		this.senderMessages = senderMessages;
	}

	/**
	 * Getters and Setters.
	 */
	public String getsenderMessages() {
		return senderMessages;
	}

	public void setsenderMessages(String senderMessages) {
		this.senderMessages = senderMessages;
	}

	
	
}
