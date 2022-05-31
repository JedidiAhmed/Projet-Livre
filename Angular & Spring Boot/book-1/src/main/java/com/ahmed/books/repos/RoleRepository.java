package com.ahmed.books.repos;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.ahmed.books.entities.Role;
//import com.ahmed.usersBackEnd.entities.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	//List<Role> getAllLivresRole();
	
	
	//List<Role> findAll();
	
	
	Role findByNomRole(String nomRole);

}
