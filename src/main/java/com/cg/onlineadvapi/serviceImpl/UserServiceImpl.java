package com.cg.onlineadvapi.serviceImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.NullUserException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;{

}

	@Override
	public User authenticateUser(String loginName, String password, HttpSession session) {
		User user= userRepository.findByLoginNameAndPassword(loginName, password);
		//If user is not empty add user in session and return loggedIn user
		//otherwise return null
		if(loginName==null && password == null) {
			throw new NullUserException("Null User Cannot Login");
		}
		if(user != null) {
			addUserInSession(user,session);
			return user;
		}
		return null;
	}

	private void addUserInSession(User user, HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("role", user.getRole());
		
	}
}