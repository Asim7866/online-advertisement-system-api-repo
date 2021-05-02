package com.cg.onlineadvapi.service;

import java.util.List;

import com.cg.onlineadvapi.domain.User;

public interface UserService {

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
	 
	 /**
	  * This is dummy method just for checking purpose
	  * @return List of user's present in the table
	  */
	 public List<User> viewAllUser();
}
