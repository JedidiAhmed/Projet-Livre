package com.ahmed.books.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Role {
	//cle primere
	@Id
	//auto incrementation
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idRole;
	private String nomRole;
	
	//
	@OneToMany (mappedBy = "role")
	@JsonIgnore
	private List<User> users;
	
	/*//Constructuer
	public Role() {
		super();
	}
	
	//Getters & setters
	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}*/

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", nomRole=" + nomRole + "]";
	}
	


}

