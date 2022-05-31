package com.ahmed.books.service;

import java.util.List;

import com.ahmed.books.entities.Role;

public interface RoleService {

	/*Role saveUser(Role u); */
	List<Role> getAllRoles();
	
	Role getRole(Long id);
}

