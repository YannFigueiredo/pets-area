package com.yannfigueiredo.petsarea.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.querydsl.EntityPathResolver;

import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.Pet;

public class OwnerDTO {
	private Long id;
	private String name;
	private String email;
	private List<PetDTO> pets = new ArrayList<>();
	
	public OwnerDTO() {}
	
	public OwnerDTO(Owner entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		entity.getPets().forEach(pet -> this.pets.add(new PetDTO(pet)));
	}
	
	/*
	public OwnerDTO(Owner entity, List<Pet> pets) {
		this(entity);
		
		pets.forEach(pet -> this.pets.add(new PetDTO(pet)));
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PetDTO> getPets() {
		return pets;
	}
}
