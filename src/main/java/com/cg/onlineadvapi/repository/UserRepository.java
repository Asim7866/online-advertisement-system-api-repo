package com.cg.onlineadvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineadvapi.domain.User;

/**
 * This repository class is responsible for all the database related operation using JpaRepository
 * @author preet classes
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
