package com.cg.onlineadvapi.exception;

/**
 * This is the Custom exception class. This exception will be thrown if user already exist and tries again to register. 
 * @author rupesh singh
 *
 */

public class UserAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserAlreadyExistException(String message){
		super(message);
	}
	

}
