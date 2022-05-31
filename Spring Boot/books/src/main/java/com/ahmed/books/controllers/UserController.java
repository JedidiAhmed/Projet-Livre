package com.ahmed.books.controllers;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
/*import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmed.books.entities.Role;
import com.ahmed.books.entities.User;
import com.ahmed.books.repos.RoleRepository;
import com.ahmed.books.security.SecurityConfig;
import com.ahmed.books.service.RoleService;
import com.ahmed.books.service.UserService;

@Controller
public class UserController {
	
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepo;
	
	
	@Autowired
	RoleService roleService;
	
	/*@Autowired
	UserDetailsService userDetailsService;*/
	
	
	
	@RequestMapping("/showCreate2")
	public String showCreate(ModelMap modelMap/*, ModelAndView modelAndView*/) {
				
		User user = new User();
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("user", user);
		
		List<Role> roles = roleService.findAll();
		modelMap.addAttribute("roles", roles);
		
		//modelAndView.addObject("action", "showCreate2");
		return "formUser";
	}
	
	

	
	
	@RequestMapping("/ListeUsers")
	public String listeUsers(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<User> use = userService.getAllUsersParPage(page, size);
		
		
		modelMap.addAttribute("users", use);
		modelMap.addAttribute("pages", new int[use.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeUsers";
	}
	
	
	@RequestMapping("/saveUser")
	public String saveUser(@Valid User user, /*Role role*/int s,
			 BindingResult bindingResult) 
	{
		if (bindingResult.hasErrors()) return "formUser";
		
		
		SecurityConfig sec = new SecurityConfig();
		PasswordEncoder passwordEncoder = sec.passwordEncoder();
      	user.setPassword(passwordEncoder.encode(user.getPassword()));
     	user.setEnabled(true);
     	
		
		System.out.println(s);
		
     	List<Role> All= roleService.findAll();
     	
     	System.out.println(All);
     	
     	System.out.println(All);
     	
     	
     	Role r1=All.get(s -1);
     	System.out.println(r1);
     	List<Role> listR = new ArrayList<Role>();
     	listR.add(r1);
     	System.out.println(listR+"Salem");
     	user.setRoles(listR);
     	System.out.println(user.getRoles()+"\nSalem");
     	System.out.println(user);
     	//Role r1=All.get((int) (role.getRole_id()-1));
     	
     	//System.out.println(r1+"hhh");
 
     	//Role r=roleRepo.findById(role.getRole_id()).get();

     	//System.out.println(r+"nnnn");
	
     	
     	//System.out.println(r.getRole());
     	
     	//System.out.println(r.getRole_id());

     	//System.out.println(role+"bbbb");


     	//System.out.println(role.getRole());
     	
     	//System.out.println(role.getRole_id());
     	
     	


     	List<Role> list=user.getRoles();
     	System.out.println(list);

     	
     	/*list.add(r);
     	user.setRoles(list);
     	
     	userService.addRoleToUser(user.getUsername(), r.getRole());*/
		userService.saveUser(user);
	    return "redirect:/ListeUsers";
	}
	
	@RequestMapping("/supprimerUser")
	public String supprimerUser(@RequestParam("id") Long id, ModelMap modelMap,

		@RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "2") int size) {

		User usr = userService.getUser(id);
		System.out.println(usr);
		usr.setRoles(null);
		userService.updateUser(usr);
		System.out.println(usr);
		userService.deleteUserById(id);
		Page<User> use = userService.getAllUsersParPage(page,
				size);
		modelMap.addAttribute("users", use);
		modelMap.addAttribute("pages", new int[use.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeUsers";
	}
	
	
	@RequestMapping("/modifierUser")
	public String editerUser(@RequestParam("id") Long id, ModelMap modelMap) {
		
		List<Role> roles = roleService.findAll();
		modelMap.addAttribute("roles", roles);
		
		User u = userService.getUser(id);
		modelMap.addAttribute("user", u);
		modelMap.addAttribute("mode", "edit");


		return "formUser";
	}

	/*@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		
		SecurityConfig sec = new SecurityConfig();
		 PasswordEncoder passwordEncoder = sec.passwordEncoder();
     	user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setPassword(user.getPassword());
     	user.setEnabled(true);
		userService.updateUser(user);
		List<User> use = userService.findAll();
		modelMap.addAttribute("users", use);
		return "listeUsers";
	}
	*/
	@RequestMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, @RequestParam("date") String date,int s,
			ModelMap modelMap) throws ParseException {
		SecurityConfig sec = new SecurityConfig();
		 PasswordEncoder passwordEncoder = sec.passwordEncoder();
     	user.setPassword(passwordEncoder.encode(user.getPassword()));
     	user.setEnabled(true);
     	List<Role> All= roleService.findAll();
     	Role r1=All.get(s -1);
    	List<Role> listR = new ArrayList<Role>();
     	listR.add(r1);
     	user.setRoles(listR);
     	List<Role> list=user.getRoles();
		userService.updateUser(user);
		List<User> use = userService.findAll();
		modelMap.addAttribute("users", use);
		return "listeUsers";
	}
	 
	
	


}