package com.cg.onlineadvertisementsystem.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	MockHttpSession session;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	//stubs
	private User user7;
	private User user8;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		user7=new User("admintemp","adminpass",1);
		user8=new User();
	}
	
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
		Exception ex = assertThrows(NullPointerException.class, () ->userServiceImpl.authenticateUser(user8.getLoginName(),user8.getPassword() , session));
		assertEquals("Null User Cannot Login", ex.getMessage());
	}
	
}
