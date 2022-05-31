package com.ahmed.loaderBalancer;

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
public class Genre {
	//cle primere
	@Id
	//auto incrementation
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idGen;
	

	private String nomGen;
	

	private String descriptionGen;
	//
	@JsonIgnore
	@OneToMany (mappedBy = "genre")
	private List<Book> livres;
	@Override
	public String toString() {
		return "Genre [idGen=" + idGen + ", nomGen=" + nomGen + ", descriptionGen=" + descriptionGen + "]";
	}
	
	/*
	//Constructeur
	public Genre() {
		super();
	}
	
	
	
	//Getters & setters
	public Long getIdGen() {
		return idGen;
	}
	public void setIdGen(Long idGen) {
		this.idGen = idGen;
	}
	public String getNomGen() {
		return nomGen;
	}
	public void setNomGen(String nomGen) {
		this.nomGen = nomGen;
	}*/
	

	
}


	
