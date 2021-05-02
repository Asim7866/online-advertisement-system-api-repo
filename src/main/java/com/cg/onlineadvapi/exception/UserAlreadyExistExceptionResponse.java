package com.cg.onlineadvapi.exception;

/**
 * This is the custom response thrown back to the user once UserAlreadyExistException occoured. 
 * @author rupesh singh
 *
 */
public class UserAlreadyExistExceptionResponse {
	
	private String userAlreadyExist;
	
	

	public UserAlreadyExistExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserAlreadyExistExceptionResponse(String userAlreadyExist) {
		super();
		this.userAlreadyExist = userAlreadyExist;
	}



	public String getUserAlreadyExist() {
		return userAlreadyExist;
	}



	public void setUserAlreadyExist(String userAlreadyExist) {
		this.userAlreadyExist = userAlreadyExist;
	}
	
	
	
}
