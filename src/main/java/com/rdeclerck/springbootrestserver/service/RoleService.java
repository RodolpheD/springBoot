package com.rdeclerck.springbootrestserver.service;

import java.util.Collection;
import java.util.stream.Stream;

import com.rdeclerck.springbootrestserver.model.Role;

public interface RoleService {
	
	Role findByRoleName(String roleName);
	
	Collection<Role> getAllRoles();
	
	Stream<Role> getAllRolesStream();
	

}
