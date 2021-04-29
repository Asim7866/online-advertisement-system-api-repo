package com.cg.onlineadvapi.serviceImpl;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
import com.cg.onlineadvapi.exception.LoginNameNotFoundException;
import com.cg.onlineadvapi.exception.UserPwdNotFoundException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	EncryptPwdServiceImpl encryptPwdImpl;
	
	//private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Override
	public User saveOrUpdate(User user) {
		user.setPassword(encryptPwdImpl.getMd5(user.getPassword()));
		user.setConfirmPassword(encryptPwdImpl.getMd5(user.getConfirmPassword()));
		return userRepository.save(user);
	}


	@Override
	public User authenticateUser(String loginName, String password, HttpSession session) {
		User user =null;
		if(loginName==null || password==null) {
			throw new FieldCannotBeBlankException("Field cannot Be Blank");
		}
		if ((user =userRepository.findByLoginName(loginName))== null) {
			throw new LoginNameNotFoundException("User with loginName"+loginName+"does not exits");
		}
		if((user.getPassword()).equals(encryptPwdImpl.getMd5(password))) {
			addUserInSession(user , session);
			return user;
		}
		else {
			throw new UserPwdNotFoundException("Login Failed || Invalid Credentials ");
		}
	}
		
	
		private void addUserInSession(User user, HttpSession session) {
			// TODO Auto-generated method stub
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userRole", user.getRole());
			
		}

}
