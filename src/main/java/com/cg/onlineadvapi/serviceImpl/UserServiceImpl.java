package com.cg.onlineadvapi.serviceImpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import com.cg.onlineadvapi.exception.AdvertiseIdException;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
import com.cg.onlineadvapi.exception.NoUserException;
import com.cg.onlineadvapi.exception.NullUserException;
import com.cg.onlineadvapi.exception.PasswordMismatchException;
import com.cg.onlineadvapi.exception.UserAlreadyExistException;
import com.cg.onlineadvapi.exception.UserNotFoundException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
/**
 *  This class is the implementation of UserService interface 
 *	This class is responsible to authenticate user while log in
 * @author Sarvesh Barve
 *
 */
/**
 * @author Abhishek
 */

/**
 * This class is the implementation of UserService interface 
 * This class is responsible for saving the user into the database using repository class and will handle the exception if any.
 * @author rupesh singh
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//Dependency
	@Autowired
	private UserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);
	
	//////////shivam:
	@Override
	public String deleteUser(int userId) {
		// delete User using UserId
		logger.info("Deleting User with UserID="+userId);
		
		try{userRepository.deleteById(userId);}
		
		catch(Exception e) {
			logger.error("No user found with UserID="+userId);
			return("UserId not found");} //if user id not found, error message is returned 
		
		logger.info("User deleted with UserID="+userId);
		return "User Deleted Successfully";	//if user id found, successful execution message is returned
	}
	
	
	//////////////vishal:
	
	@Override
	public List<User> viewUserList() {
		logger.info("For finding all USERS");
		// Method to return list of all users, null if no users found.
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()) {
			throw new NoUserException("No User Found");
		}
		return userList;
	}
	
	@Override
	public User viewUser(int userId) {
		logger.info("For finding USERS by ID");
		// Returns user by UserId or null if not found
		User user = userRepository.findByUserId(userId);
		if(user==null) {
			throw new NoUserException("User with userid "+userId+" doesn't Exist");
		}
		return user;
	}

	
	@Override
	public User saveUser(User user){
	
		logger.info("Inside Service Implementation class");
		logger.info("Method to save user has been started");
		User userToBeSave = user;
		
		/**
		 * Logic to check if Password and Confirm Password both are same or not
		 */
		
		if(!userToBeSave.getPassword().equals(userToBeSave.getConfirmPassword())) {
			throw new PasswordMismatchException("(E)Password and Confirm Password should be matching");
		}
		
		
		/**
		 * Logic to check if user is specifying correct role or not
		 */
		if(userToBeSave.getRole()!= 1 && userToBeSave.getRole()!= 2) {
			throw new NumberFormatException("(E)Cannot enter value other than 1 and 2");
		}
		
		/**
		 * Logic to check user with the same login name already exist or not
		 */
		
		String newLoginName = userToBeSave.getLoginName();
		List<User> userList = userRepository.findAll();
		for(User user1:userList) {
			String existingUserName = user1.getLoginName();
			if(existingUserName.equals(newLoginName)) {
				logger.error("Exception occoured because "+ newLoginName +" already exist");
				throw new UserAlreadyExistException("(E)User Already Exist");
			}
		}
		
		/**
		 * This function will add the user into database
		 */
		
		logger.info("User has been registered");
		return userRepository.save(user);
	}
	
	

	@Override
	public User authenticateUser(String loginName, String password, HttpSession session) {
		
		User user = null;
    if(loginName==null && password == null) {
			throw new NullUserException("Null User Cannot Login");
		}
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

  @Override
	public User saveOrUpdateUser(User user) {
		if(user == null) {
			throw new NullPointerException("User Object cannot be null");
		}
		user.setRole(2);
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer user_id) {
		 Optional<User> user = userRepository.findById(user_id);
		 if(user.isPresent()) {
			 userRepository.deleteById(user_id);
		 }else {
			 throw new AdvertiseIdException(user_id+" not found");
		 }

}
}

