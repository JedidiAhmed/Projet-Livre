package com.ahmed.books.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahmed.books.entities.User;
import com.ahmed.books.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Autowired
UserService userService;
@Override
public UserDetails loadUserByUsername(String email) throws
UsernameNotFoundException {
	User user = userService.findUserByEmail(email);
	if (user==null)
	throw new UsernameNotFoundException("Utilisateur introuvable !");
	List<GrantedAuthority> auths = new ArrayList<>(); 
	/*user.getRoles().forEach(role -> {
	GrantedAuthority auhority = new
	SimpleGrantedAuthority(role.getRole());
	auths.add(auhority);
	});*/
	GrantedAuthority auhority = new SimpleGrantedAuthority(user.getRole().getNomRole());
	auths.add(auhority);
	return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),auths);
	}
	}