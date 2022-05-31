package com.ahmed.books.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.ahmed.books.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  {

	Role findByRole(String role);
	//OptionalList<Role> findByRole_id(Long role_id);
	//<Role> findById(Long id);
	/*Role findByRole_id(Long role_id);*/
	Optional<Role> findById(Long id);
}
