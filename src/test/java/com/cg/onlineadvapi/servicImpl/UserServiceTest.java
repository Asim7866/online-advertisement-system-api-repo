package com.cg.onlineadvapi.servicImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.PasswordMismatchException;
import com.cg.onlineadvapi.exception.UserAlreadyExistException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;
import com.cg.onlineadvapi.web.UserController;


class UserServiceImplTest {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	User user;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
	}
	
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
		assertThrows(PasswordMismatchException.class, ()->userServiceImpl.saveUser(new User("Rupesh", "RupeshSingh", "Rupesh12346", "Rupesh1234", "8452922052", "sunny@gmail.com", 1)));
	}
	
	@Test
	public void test_saveUser_ShouldThrowNumberFormatException() throws Exception{
		BDDMockito.given(userRepository.save(user)).willThrow(new NumberFormatException());
		assertThrows(NumberFormatException.class, ()->userServiceImpl.saveUser(new User("Rupesh", "RupeshSingh", "Rupesh1234", "Rupesh1234", "8452922052", "sunny@gmail.com", 3)));
	}
	

}
