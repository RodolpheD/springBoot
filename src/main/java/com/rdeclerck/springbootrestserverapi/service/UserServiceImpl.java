package com.rdeclerck.springbootrestserverapi.service;

import java.util.Collection;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdeclerck.springbootrestserverapi.dao.UserRepository;
import com.rdeclerck.springbootrestserverapi.model.User;

@Service(value = "userService")// l'annotation @Service est optionnelle ici, car il n'existe qu'une seule implémentation
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public Collection<User> getAllUsers() {
		return IteratorUtils.toList(userRepository.findAll().iterator());
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}


	@Override
	@Transactional(readOnly=false)
	public User saveOrUpdateUser(User user) {
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteUser(Long id) {
		userRepository.delete(id);
		
	}

}
