package com.rdeclerck.springbootrestserverapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdeclerck.springbootrestserverapi.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByLogin(String login);

}
