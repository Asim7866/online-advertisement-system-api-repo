package com.cg.onlineadvapi.service;

import javax.servlet.http.HttpSession;

import com.cg.onlineadvapi.domain.User;
/**
 * This UserService is responsible for performing user related task.
 * @author Sarvesh Barve
 *
 */
public interface UserService {
	

	/**
	 * This authenticateUser will authenticate the User and Send authenticated User if user is available in database
	 * @param loginName of the user
	 * @param password of the user
	 * @param session
	 * @return authenticated User 
	 */
	public User authenticateUser(String loginName , String password , HttpSession session);

	

}
