package com.ahmed.books.service;

import java.util.List;

import com.ahmed.books.entities.Role;
import com.ahmed.books.entities.User;

public interface UserService {
	

	User saveUser(User u); 
	void deleteUserById(Long id); 
	List<User> getAllUsers();
	User getUser(Long id);
	User updateUser(User u);
	
	User findUserByUsername(String username);
	User findUserByEmail(String email);
	
	
	Role addRole(Role role);
	User addRoleToUser(String email, String rolename);
	
}
