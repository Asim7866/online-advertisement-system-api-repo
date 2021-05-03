package com.cg.onlineadvapi.service;

import java.util.List;

import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.domain.User;

/**
 * This AdminOperation Service is responsible for performing all the business logic on
 * Admin operations. 
 * @author Vishal
 */
public interface UserService {
	
	
	
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
