package com.cg.onlineadvapi.serviceImplTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.AdminServiceImpl;

class AdminServiceImplTest {
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private AdminServiceImpl adminServiceImpl;
	
	private User user;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		
	}
	
	@Test
	public void test_viewUserList_ShouldReturnListOfUser() {
		User user = new User("shivam","adm");
		User user1 = new User("vishal","admin");
		List<User> vishal = new ArrayList<>(); 
		vishal.add(user);
		vishal.add(user1);
		BDDMockito.given(userRepository.findAll()).willReturn(vishal);
		
		List<User> shivam = adminServiceImpl.viewUserList();
		assertEquals(vishal.size(),shivam.size());
		
	}
	
	@Test
	public void test_viewUser_GivenUserId_ShouldReturnUser() {
		User user = new User("vishal","admin");
	
		BDDMockito.given(userRepository.findByUserId(user.getUserId()))
		.willReturn(user);
		
		
		assertEquals("vishal",user.getname());
		assertEquals("admin", user.getPassword());
	}

	

}
