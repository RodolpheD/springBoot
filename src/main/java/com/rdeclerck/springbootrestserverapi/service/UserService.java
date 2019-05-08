package com.rdeclerck.springbootrestserverapi.service;

import java.util.Collection;

import com.rdeclerck.springbootrestserverapi.model.User;

public interface UserService {
	
	Collection<User> getAllUsers();
	
	User getUserById(Long id);
	
	User findByLogin(String login);
	
	User saveOrUpdateUser(User user);
	
	void deleteUser(Long id);
	
	

}
