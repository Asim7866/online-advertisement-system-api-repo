package com.cg.onlineadvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineadvapi.domain.User;

/**
 * 
 * @author abhishek 
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findUserByLoginNameAndPassword(String loginName, String password);
	
}
