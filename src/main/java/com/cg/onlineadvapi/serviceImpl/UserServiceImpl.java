package com.cg.onlineadvapi.serviceImpl;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
//import com.cg.onlineadvapi.exception.PasswordNotFoundException;
import com.cg.onlineadvapi.exception.UserNotFoundException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;

/**
 * 
 * @author Sarvesh Barve
 *
 */

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	EncryptPwdServiceImpl encryptPwdImpl;
	
	
	//private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	
	
//	@Override
//	public User saveOrUpdate(User user) {
//		//log.info("Password is getting encrypted");
//		//user.setPassword(encryptPwdImpl.getMd5(user.getPassword()));
//		//user.setConfirmPassword(encryptPwdImpl.getMd5(user.getConfirmPassword()));
//		return userRepository.save(user);
//	}

	
	@Override
	public User authenticateUser(String loginName, String password, HttpSession session) {
		
		User user = null;
		
		if(verifyloginName(loginName)&&verifyPassword(password)) {
			if(loginName==null && password==null) {
				throw new FieldCannotBeBlankException("Please Enter LoginName and Password to login");
			}
			//if((user = userRepository.findByLoginNameAndPassword(loginName,(encryptPwdImpl.getMd5(password))))==null) {
			if((user = userRepository.findByLoginNameAndPassword(loginName,password))==null) {
				throw new UserNotFoundException("Login Failed || Invalid Credentials");
			}
		}
			addUserInSession(user , session);
			return user;

		}
	/**
	 * This method checks if the password is valid or not.
	 * @param password of the User
	 * @return true
	 */
	private boolean verifyPassword(String password) {	
	if(password==null) {
		log.info("If Password is blank");
		log.error("Password should not be Blank");
		throw new FieldCannotBeBlankException("Password should not be Blank");
	}
	if(password.length()>12) {
		throw new UserNotFoundException("Password should be less than 12 character ");
	}
	if(password.length()<7) {
		throw new UserNotFoundException("Password must be atleast 8 character");
	}
	
	return true;
}
	
	/**
	 * This method checks whether the login name is valid or not.
	 * @param loginName of the user 
	 * @return true
	 */
	private boolean verifyloginName(String loginName) {
	if(loginName==null) {
		log.info("If loginName is Blank");
		log.error("LoginName should not be Blank");
		throw new FieldCannotBeBlankException("LoginName should not be Blank ");
	}
	return true;
}

	
		private void addUserInSession(User user, HttpSession session) {
			// TODO Auto-generated method stub
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userRole", user.getRole());
			
		}

}
