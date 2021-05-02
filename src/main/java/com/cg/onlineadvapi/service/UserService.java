package com.cg.onlineadvapi.service;

import java.util.List;
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
/**
 * This UserService is responsible for all the business logic on User operations
 * @author shivamt
 *
 */
public interface UserService {
	/**
	 * This deleteUser method will delete existing user data from database using User ID
	 * @param User Id of the, user - to be deleted
	 * @return User which is deleted
	 * @author shivam
	 */
	public String deleteUser(int userId);
	
	/**
	 * This viewUserList method will return List of all Users from database
	 * @return List<User> if found, otherwise null
	 */
	public List<User> viewUserList();
	
	/**
	 * This viewUser method return data of required User from database
	 * @param userId of User
	 * @return User
	 */
	public User viewUser(int userId);

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
	/**
	 * This method is used to save Or update user details managed by user module
	 * @param user
	 * @return
	 */
	 public User saveOrUpdateUser(User user);
	 
	 /**
	  * This method is used to delete user by user_id managed by user module
	  * @param user_id
	  */
	 public void deleteById(Integer user_id);
	 

}
