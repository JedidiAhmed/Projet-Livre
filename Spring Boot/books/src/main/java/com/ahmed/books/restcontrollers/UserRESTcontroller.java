package com.ahmed.books.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.books.entities.Genre;
import com.ahmed.books.entities.User;
import com.ahmed.books.entities.Book;
import com.ahmed.books.service.BookService;
import com.ahmed.books.service.UserService;

@RestController
@RequestMapping("/apiiuser")
@CrossOrigin
public class UserRESTcontroller {
	
	@Autowired
	UserService userService;
	
	/*
	 @Override
	public User addRoleToUser(String username, String rolename) {
		User usr = UserRepository.findByUsername(username);
		Role r = roleRep.findByRole(rolename);
		
		usr.getRoles().add(r);
		return usr;
	}
	 */
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers()
	{
		return userService.findAll();
	}
	
	
	//@RequestMapping(method = RequestMethod.PUT)
	@RequestMapping(value="/{nom}/{role}",method = RequestMethod.PUT)
	public User changeUser(@PathVariable("nom") String nom, @PathVariable("role") String role ) {
		
	User abc= userService.addRoleToUser(nom,role);
	return userService.updateUser(abc);
	}

}
