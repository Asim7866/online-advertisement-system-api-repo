package com.cg.onlineadvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineadvapi.domain.User;


/**
 * This repository is responsible  for operations on the database .
 * @author abhishek 
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	/**
	 * This method finds User by LoginName and Password
	 * @param loginName of the user 
	 * @param password of the user
	 * @return user
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
