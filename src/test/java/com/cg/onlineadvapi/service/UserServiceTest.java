package com.cg.onlineadvapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.AdvertiseIdException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;
/**
 * 
 * @author bmanikch
 *
 */
public class UserServiceTest {
	
	@Mock //- mock bean
	private UserRepository userRepository;
	
	@Autowired
	@InjectMocks // mock bean injection
	private UserServiceImpl userServiceImpl;
	// stubs
	private User firstUser;
	private User secondUser;
	private User user; 

	@BeforeEach
	public void setUp() {
	MockitoAnnotations.initMocks(this); //invoke mock
	user=new User();
	
//	secondUser = new User("dev","9699086723","dev@gmail.com",2);
	}
	
	@AfterEach
	public void tearDown() {
	userRepository.deleteAll();
	firstUser = secondUser = null;
	}
	
	@Test
	public void test_saveOrUpdateUser_GivenUser_ShouldReturnSavedUser(){
	firstUser = new User("bipin","9699086721","bipin@gmail.com",2);
	BDDMockito.given(userRepository.save(user))
	.willReturn(firstUser);
	secondUser = userServiceImpl.saveOrUpdateUser(user);
	assertNotNull(secondUser);
	assertEquals("bipin",secondUser.getname());
	assertEquals("bipin@gmail.com",secondUser.getEmail());
	assertEquals("9699086721",secondUser.getContactNo());
	assertEquals(2,secondUser.getRole());
	}
	
	@Test
	public void test_saveOrUpdateUser_GivenUser_ShouldReturnUpdatedUser(){
	firstUser = new User("bipin","9699086721","bipin@gmail.com",2);
	secondUser = new User("dev","9699086723","dev@gmail.com",2);
	BDDMockito.given(userRepository.save(user))
	.willReturn(firstUser);
	secondUser = userServiceImpl.saveOrUpdateUser(user);
	assertNotNull(firstUser);
	assertNotNull(secondUser);
	assertEquals(firstUser,secondUser);
	}
	
//	@Test
//	public void test_deleteUserByUserId_GivenUserId_ShouldDeleteUserById() {
//	userServiceImpl.deleteById(1);
//	verify(userRepository).deleteById(1);
//	System.out.println(firstUser.getUserId());
//	assertNull(firstUser.getUserId());
//	assertNull(user.getname());
//	assertNull(user.getRole());
//	assertNull(user.getContactNo());
//	assertNull(user.getEmail());
//	}
   
	@Test
	public void test_saveOrUpdateUser() throws Exception {
		BDDMockito.given(userRepository.save(user))
		.willThrow(new NullPointerException());
		assertThrows(NullPointerException.class, ()->userServiceImpl.saveOrUpdateUser(user));
	}
	
	@Test
	public void test_deleteById() throws Exception {
		BDDMockito.given(userRepository.findById(user.getUserId()))
		.willThrow(new AdvertiseIdException());
		assertThrows(AdvertiseIdException.class, ()->userServiceImpl.deleteById(1));
	}
}
