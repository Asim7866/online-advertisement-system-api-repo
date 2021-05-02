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
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;

class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	private User user;
	private User user1;
	private User user2;
	private List<User> userList;	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		userList = new ArrayList<>(); 
		
	}
	
	@Test
	public void test_viewUserList_ShouldReturnListOfUser() {
		user2 = new User("shivam","adm");
		user1 = new User("vishal","admin");
		
		userList.add(user2);
		userList.add(user1);
		BDDMockito.given(userRepository.findAll()).willReturn(userList);
		
		List<User> fetchData = userServiceImpl.viewUserList();
		assertNotNull(userList);
		assertNotNull(fetchData);
		assertEquals(2,fetchData.size());
		assertEquals(user2, fetchData.get(0));
		assertEquals(user1, fetchData.get(1));
	}
	
	@Test
	public void test_viewUser_GivenUserId_ShouldReturnUser() {
	User user = new User("vishal","admin");

		BDDMockito.given(userRepository.findByUserId(0)).willReturn(user);
		User fetchedUser =userServiceImpl.viewUser(0);
		assertEquals(user,fetchedUser);
//		assertEquals(user.getPassword(), fetchedUser.getPassword());
	}
	

}
