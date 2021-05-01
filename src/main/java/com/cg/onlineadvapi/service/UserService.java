package com.cg.onlineadvapi.service;

import com.cg.onlineadvapi.domain.User;

/**
 * This interface will have all the necessary method declaration.
 * @author rupesh singh
 *
 */
public interface UserService {
	/**
	 * 
	 * @param user
	 * @return this function will return saved user otherwise exception if user already exist
	 */
	public User saveUser(User user);
}
