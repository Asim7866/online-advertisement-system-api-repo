package com.cg.onlineadvapi.service;

public interface UserService {
	/**
	 * This deleteUser method will delete existing user data from database using User ID
	 * @param User Id of the, user - to be deleted
	 * @return User which is deleted
	 * @author shivam
	 */
	public String deleteUser(int userId);
}
