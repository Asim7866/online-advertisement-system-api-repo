package com.cg.onlineadvapi.serviceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.FieldCannotBeBlankException;
//import com.cg.onlineadvapi.exception.PasswordNotFoundException;
import com.cg.onlineadvapi.exception.UserNotFoundException;
import com.cg.onlineadvapi.repository.UserRepository;
/**
 * 
 * @author Sarvesh Barve
 *
 */
@SpringBootTest
public class UserServiceImplTest {
	

	@Mock
	MockHttpSession session;
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	
	//stubs
	private User user;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	
	
	@Mock
	UserRepository userRepository;
//	@Autowired
//	EncryptPwdServiceImpl encryptPwd;
//		
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		 user1 = new User ("sarvesh11","sarvesh123");
		 user2 =new User ("sarve","sarvesh1234");
		 user3 = new User ("sarvesh1","sarves12345");
		 user4 = new User(null,"sarvesh");
		 user5 = new User(null,"sarv");
		 user6 = new User(null,"sarvesh12345678");
		 user = new User();
	}
	
	/**
	 * Test Case with Null LoginName 
	 */
	@Test
	void test_authenticateUser_GivenNullLoginName_ShouldThrowFielCannotBeBlankException(){
		BDDMockito.given(session.getAttribute("loginName")).willReturn("sarvesh1");
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
		BDDMockito.given(session.getAttribute("loginName")).willReturn("sarvesh1");
		Exception ex = assertThrows(FieldCannotBeBlankException.class,() -> userServiceImpl.authenticateUser(user3.getLoginName(),user.getPassword(), session));
		assertNull(user.getPassword());
		assertNotNull(user3.getLoginName());
		assertEquals("Password should not be Blank", ex.getMessage());
	}
	
	/**
	 * Test Case to authenticate 
	 * 
	 */
	@Test
	void test_authenticateUser_GivenUser_ShouldReturnLoggedInUser() {  //"4d5be6989cbc9949c1ff10eb4749bfef"
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user1.getLoginName(), user1.getPassword())).willReturn(new User("sarvesh11","sarvesh123"));
		BDDMockito.given(session.getAttribute("loginName")).willReturn(user1.getLoginName());
		User loginUser = userServiceImpl.authenticateUser("sarvesh11","sarvesh123",session); //(user1.getLoginName(),user1.getPassword(),session);
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
	void test_authenticateUser_GivenWrongUser_ShouldReturnUserNotFound() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user2.getLoginName(), user2.getPassword())).willReturn(new User("sarve","sarvesh1234"));
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser("sarvesh", user2.getPassword(), session));
		assertEquals("Login Failed || Invalid Credentials", ex.getMessage());
	}
	
	/**
	 * Test Case with the wrong password.
	 */
	@Test
	void test_authenticateUser_GivenWrongPassword_ShouldReturnUserNotFound() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword())).willReturn(new User("sarvesh1","sarvesh12345"));
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(), user2.getPassword(), session));
		assertEquals("Login Failed || Invalid Credentials", ex.getMessage());
		
	}
	/**
	 * Test case to check exception is thrown when password is less than 8 character
	 */
	
	@Test
	void test_authenticateUser_GivenPasswordLessThan8Character_ShouldReturnUserNotFound() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword())).willThrow(UserNotFoundException.class);
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(),user5.getPassword(), session));
		assertEquals("Password must be atleast 8 character", ex.getMessage());
		
	}
	/**
	 * Test case to check exception is thrown when password is greater then 12 character+
	 */
	
	@Test
	void test_authenticateUser_GivenPasswordGreaterThan12Character_ShouldReturnUserNotFound() {
		BDDMockito.given(userRepository.findByLoginNameAndPassword(user3.getLoginName(), user3.getPassword())).willThrow(UserNotFoundException.class);
		Exception ex = assertThrows(UserNotFoundException.class, () ->userServiceImpl.authenticateUser(user3.getLoginName(),user6.getPassword(), session));
		assertEquals("Password should be less than 12 character ", ex.getMessage());
		
	}
	
}
