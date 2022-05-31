package com.ahmed.books.service;

import java.util.List;

import com.ahmed.books.entities.Role;



public interface RoleService {

	List <Role> findAll();
	Role findByRole(String role);



}
