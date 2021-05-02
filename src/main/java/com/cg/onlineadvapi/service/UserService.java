package com.cg.onlineadvapi.service;

import javax.servlet.http.HttpSession;

import com.cg.onlineadvapi.domain.User;

/**
 * This UserService is responsible for performing user related task.
 * @author Sarvesh Barve
 * 
 *
 */
/**
 * This UserService is responsible for performing user related task.
 * @author Rupesh Singh
 *
 */
/**
 * This UserService is responsible for performing user related task.
 * @author Abhishek
 *
 */

public interface UserService {
	
	//User Registration
		/**
		 * 
		 * @param user
		 * @return this function will return saved user otherwise exception if user already exist
		 */
		public User saveUser(User user);
		
	//User Login	
		/**
		 * This authenticateUser will authenticate the User and Send authenticated User if user is available in database
		 * @param loginName of the user
		 * @param password of the user
		 * @param session
		 * @return authenticated User 
		 */
		public User authenticateUser(String loginName , String password , HttpSession session);


}
