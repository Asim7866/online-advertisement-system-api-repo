package com.cg.onlineadvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineadvapi.domain.User;
/**
 * This repository is responsible  for operations on the database .
 * @author Sarvesh Barve
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//User findByLoginNameAndPassword(String loginName, String md5);
	/**
	 * This method finds user by login name
	 * @param loginName of user
	 * @return User 
	 */
	User findByLoginName(String loginName);


	

}
