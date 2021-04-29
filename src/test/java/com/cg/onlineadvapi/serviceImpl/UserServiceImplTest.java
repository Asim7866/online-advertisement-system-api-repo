package com.cg.onlineadvapi.serviceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
import com.cg.onlineadvapi.exception.LoginNameNotFoundException;
import com.cg.onlineadvapi.exception.UserPwdNotFoundException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	MockHttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EncryptPwdServiceImpl encryptPwd;
		

	@Test
	void test_saveOrUpdateUser_GivenUserShouldReturn_SavedUser(){
		User user =  new User();
		user.setname("Sarvesh");
		user.setLoginName("sarvesh1");
		user.setPassword("sarvesh1234");
		user.setConfirmPassword("sarvesh1234");
		user.setContactNo("8945623145");
		user.setEmail("sarvesh@gmail.com");
		//user.setRole(1);
		User savedUser = userService.saveOrUpdate(user);
		assertEquals(user,savedUser);
		
	}
	
	/**
	 * Test Case with Null 
	 */
	@Test
	void test_authenticateUser_GivenNull_ShouldReturn_FieldisBlank() {
		String loginName=null;
		String password ="0000000000";
	
		assertThrows(FieldCannotBeBlankException.class,() -> userService.authenticateUser(loginName, password, session));
	}
	
	/**
	 * Test Case to Check If User is getting login.
	 * 
	 */
	
	@Test
	void test_authenticateUser_GivenUser_ShouldReturn_LoggedInUser() {
//		User user = new User();
//		user.setLoginName("sarvesh1");
//		user.setPassword("sarvesh1234");
		
		String loginName="sarvesh1";
		String password ="sarvesh1234";
		User loggedinUser =userService.authenticateUser(loginName,password, session);
		assertEquals(loginName,loggedinUser.getLoginName());
		assertEquals( encryptPwd.getMd5(password), loggedinUser.getPassword());

		
	}
	
	/**
	 * Test Case with wrong loginName
	 * 
	 */
	
	
	
	@Test
	void test_authenticateUser_GivenWrongUser_ShouldReturn_LoginNameNotFound() {
		String loginName="sarvesh44";
		String password ="0000000000";
	
		assertThrows(LoginNameNotFoundException.class,() -> userService.authenticateUser(loginName, password, session));
	}
	
	/**
	 * Test Case with the wrong password.
	 */
	
	@Test
	void test_authenticateUser_GivenWrongPassword_ShouldReturn_InvalidCredentials() {
		String loginName="sarvesh1";
		String password ="0000000000";
	
		assertThrows(UserPwdNotFoundException.class,() -> userService.authenticateUser(loginName, password, session));
	}
	
	
	
	
	
}
