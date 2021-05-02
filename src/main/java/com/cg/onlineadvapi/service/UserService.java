package com.cg.onlineadvapi.service;

import java.util.List;

import com.cg.onlineadvapi.domain.User;
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
}
