package com.rdeclerck.springbootrestserverapi.service;

import java.util.Collection;
import java.util.stream.Stream;

import com.rdeclerck.springbootrestserverapi.model.Role;

public interface RoleService {
	
	Role findByRoleName(String roleName);
	
	Collection<Role> getAllRoles();
	
	Stream<Role> getAllRolesStream();
	

}
