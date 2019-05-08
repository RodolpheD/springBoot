package com.rdeclerck.springbootrestserver.controller;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rdeclerck.springbootrestserver.model.Role;
import com.rdeclerck.springbootrestserver.model.User;
import com.rdeclerck.springbootrestserver.service.RoleService;
import com.rdeclerck.springbootrestserver.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:8080, maxAge = 3600")
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value="/users")
	public ResponseEntity<Collection<User>> getAllUsers(){
		Collection<User> users = userService.getAllUsers();
		logger.info("liste des utilisateurs : " + users.toString());
		return new ResponseEntity<Collection<User>>(users,HttpStatus.FOUND);
	
	}
	
	@PostMapping(value = "/users")
	@Transactional
	public ResponseEntity<User> saveUser(@RequestBody User user){
		Set<Role> roles = new HashSet<>();
		Role roleUser = new Role("ROLE_USER");//initialisation du rôle ROLE_USER
		roles.add(roleUser);
		user.setRoles(roles);
		user.setActive(0);
		Set<Role> roleFromDB = extractRole_Java8(user.getRoles(), roleService.getAllRolesStream());
		user.getRoles().removeAll(user.getRoles());
		user.setRoles(roleFromDB);
		User userSave = userService.saveOrUpdateUser(user);
		logger.info("userSave : " + userSave.toString());
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	private Set<Role> extractRole_Java8(Set<Role> rolesSetFromUser, Stream<Role> roleStreamFromDB){
		// Collect UI role names
		Set<String> uiRoleNames = rolesSetFromUser.parallelStream().map(Role::getRoleName).collect(Collectors.toCollection(HashSet::new));
		// Filter DB roles
		return roleStreamFromDB.filter(role -> uiRoleNames.contains(role.getRoleName())).collect(Collectors.toSet());
		
	}
	
	
}
