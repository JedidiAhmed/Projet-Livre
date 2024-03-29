package com.ahmed.books.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.books.entities.Role;
import com.ahmed.books.service.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin

public class RoleRESTController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Role> getAllRoles()
	{
		return roleService.getAllRoles();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Role getRole(@PathVariable("id") Long id)
	{
		return roleService.getRole(id);
	}
	
}
