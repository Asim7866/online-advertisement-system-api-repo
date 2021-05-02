package com.cg.onlineadvertisementsystem.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
import com.cg.onlineadvapi.exception.NullUserException;
import com.cg.onlineadvapi.exception.PasswordMismatchException;
import com.cg.onlineadvapi.exception.UserNotFoundException;
//import com.cg.onlineadvapi.exception.UserAlreadyExistException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;
import com.cg.onlineadvapi.web.UserController;

/**
 * 
 * @author Sarvesh Barve
 *
 */
/**
 * 
 * @author Abhishek
 *
 */
class UserServiceImplTest {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	User user;
	
	@Mock
	MockHttpSession session;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	private User user7;
	private User user8;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user1 = new User ("sarvesh11","sarvesh123");
		 user2 =new User ("sarve","sarvesh1234");
		 user3 = new User ("sarvesh1","sarves12345");
		 user4 = new User(null,"sarvesh");
		 user5 = new User(null,"sarv");
		 user6 = new User(null,"sarvesh12345678");
		 user7=new User("admintemp","adminpass");
		 user8=new User();
		
	}
	//*******************Test Cases For User Registration**********************//
	@Test
	public void test_saveUser_GivenTheUser_ShouldReturnSavedUser() {
		User user1 = new User("Rupesh", "RupeshSingh", "Rupesh1234", "Rupesh1234", "8452922052", "sunny@gmail.com", 1);
		
		BDDMockito.given(userRepository.save(user1)).willReturn(user1);
		
		User userReturnedFromServiceImpl = userServiceImpl.saveUser(user1);
		
		assertNotNull(userReturnedFromServiceImpl);
		assertEquals(user1,userReturnedFromServiceImpl);		
	}
	/*
	@Test
	public void test_saveUser_ShouldThrowUserAlreadyExistException() throws Exception{
		User user = new User("Rupesh", "RupeshSingh", "Rupesh1234", "Rupesh1234", "8452922052", "sunny@gmail.com", 1);
		BDDMockito.given(userRepository.save(user)).willThrow(new UserAlreadyExistException());
		assertThrows(UserAlreadyExistException.class, ()->userServiceImpl.saveUser(new User("Rupesh", "RupeshSingh", "Rupesh1234", "Rupesh1234", "8452922052", "sunny@gmail.com", 1)));
		
	}*/
	
	@Test
	public void test_saveUser_ShouldThrowPasswordMisMatchException() throws Exception{
		BDDMockito.given(userRepository.save(user)).willThrow(new PasswordMismatchException());
		Exception ex = assertThrows(PasswordMismatchException.class, ()->userServiceImpl.saveUser(new User("Rupesh", "RupeshSingh", "Rupesh12346", "Rupesh1234", "8452922052", "sunny@gmail.com", 1)));
		assertEquals("(E)Password and Confirm Password should be matching", ex.getMessage());
	}
	
	@Test
	public void test_saveUser_ShouldThrowNumberFormatException() throws Exception{
		BDDMockito.given(userRepository.save(user)).willThrow(new NumberFormatException());
		assertThrows(NumberFormatException.class, ()->userServiceImpl.saveUser(new User("Rupesh", "RupeshSingh", "Rupesh1234", "Rupesh1234", "8452922052", "sunny@gmail.com", 3)));
	}
	
	//*************************Test Cases for User Login Validation ***********************************//
	
	/**
	 * Test Case with Null LoginName 
	 */
	@Test
	void test_authenticateUser_GivenNullLoginName_ShouldThrowFielCannotBeBlankException(){
		BDDMockito.given(session.getAttribute("loginName"))
		.willReturn("sarvesh1");
		Exception ex = assertThrows(FieldCannotBeBlankException.class, ()-> userServiceImpl.authenticateUser(	user4.getLoginName(), user4.getPassword(), session));
		assertNotNull(ex.getMessage());
		assertNotNull(user4.getPassword());
		
		assertEquals("LoginName should not be Blank ", ex.getMessage());
	}
	
	/**
	 * Test Case with null Password
	 */
	@Test
	void test_authenticateUser_GivenNullPassword_ShouldThrowFielCannotBeBlankException() {
		BDDMockito.given(session.getAttribute("loginName"))
		.willReturn("sarvesh1");
		Exception ex = assertThrows(FieldCannotBeBlankException.class,() -> userServiceImpl.authenticateUser(user3.getLoginName(),user8.getPassword(), session));
		assertNull(user.getPassword());
		assertNotNull(user3.getLoginName());
		assertEquals("Password should not be Blank", ex.getMessage());
	}
	
	/**
	 * Test Case to authenticate 
	 * 
	 */
	@Test
	void test_authenticateUser_GivenUser_ShouldReturnLoggedInUser() {  
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user1.getLoginName(), user1.getPassword()))
		.willReturn(new User("sarvesh11","sarvesh123"));
		BDDMockito.given(session.getAttribute("loginName")).willReturn(user1.getLoginName());
		User loginUser = userServiceImpl.authenticateUser("sarvesh11","sarvesh123",session);
		assertNotNull(loginUser);
		assertNotNull(loginUser.getLoginName());
		assertNotNull(loginUser.getPassword());
		assertEquals(loginUser.getLoginName(),user1.getLoginName());
		assertEquals(loginUser.getPassword(),user1.getPassword());
		
		
	}
	/**
	 * Test Case with wrong loginName
	 * 
	 */
	@Test
	void test_authenticateUser_GivenWrongUser_ShouldThrowUserNotFoundException() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user2.getLoginName(), user2.getPassword()))
		.willReturn(new User("sarve","sarvesh1234"));
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser("sarvesh", user2.getPassword(), session));
		assertEquals("Login Failed || Invalid Credentials", ex.getMessage());
	}
	
	/**
	 * Test Case with the wrong password.
	 */
	@Test
	void test_authenticateUser_GivenWrongPassword_ShouldThrowUserNotFoundException() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword()))
		.willReturn(new User("sarvesh1","sarvesh12345"));
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(), user2.getPassword(), session));
		assertEquals("Login Failed || Invalid Credentials", ex.getMessage());
		
	}
	/**
	 * Test case to check exception is thrown when password is less than 8 character
	 */
	
	@Test
	void test_authenticateUser_GivenPasswordLessThan8Character_ShouldThrowUserNotFoundException() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword()))
		.willThrow(UserNotFoundException.class);
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(),user5.getPassword(), session));
		assertEquals("Password must be atleast 8 character", ex.getMessage());
		
	}
	/**
	 * Test case to check exception is thrown when password is greater then 12 character+
	 */
	
	@Test
	void test_authenticateUser_GivenPasswordGreaterThan12Character_ShouldThrowUserNotFoundException() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword()))
		.willThrow(UserNotFoundException.class);
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(),user6.getPassword(), session));
		assertEquals("Password should be less than 12 character ", ex.getMessage());
		
	}
	
	//********************************Test Cases For Login Implementation*************************//
	
	/**
	 * test case to check if the given user is getting returned
	 */
	@Test
	void test_authenticateUser_GivenUser_ShouldReturnUser() {
		
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user7.getLoginName(), user7.getPassword()))
		.willReturn(new User("admintemp","adminpass"));
		User returnUser = userServiceImpl.authenticateUser(user7.getLoginName(),user7.getPassword() , session);
		assertNotNull(returnUser.getLoginName());
		assertNotNull(returnUser.getPassword());	
	}
	
	
	/**
	 * test case to check exception is thrown when user is null 
	 */
	@Test
	void test_authenticateUser_GivenNullUser_ShouldThrowNullUserException(){
		
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user8.getLoginName(), user8.getPassword()))
		.willReturn(null);
		Exception ex = assertThrows(NullUserException.class, () ->userServiceImpl.authenticateUser(user8.getLoginName(),user8.getPassword() , session));
		assertEquals("Null User Cannot Login", ex.getMessage());
	}
}

