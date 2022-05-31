package com.ahmed.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmed.books.entities.Role;
import com.ahmed.books.repos.RoleRepository;




@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findByRole(String role) {
		// TODO Auto-generated method stub
		return new Role();
				//roleRepository.findByRole(role);
	}



}
