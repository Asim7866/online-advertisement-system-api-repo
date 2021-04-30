package com.cg.onlineadvapi.service;

import javax.servlet.http.HttpSession;

import com.cg.onlineadvapi.domain.User;


public interface UserService {

	public User authenticateUser(String loginName , String password , HttpSession session);
	
}
