package com.rdeclerck.springbootrestserverapi.service;

import java.util.Collection;
import java.util.stream.Stream;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdeclerck.springbootrestserverapi.dao.RoleRepository;
import com.rdeclerck.springbootrestserverapi.model.Role;

@Service(value="roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Collection<Role> getAllRoles(){//Avant JAVA8
		return IteratorUtils.toList(roleRepository.findAll().iterator());
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public Stream<Role> getAllRolesStream() {//JAVA8
		return roleRepository.getAllRolesStream();
	}
}
