package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.enums.Gender;

public class OwnerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	private String photo;
	private String email;
	private List<PetDTO> pets = new ArrayList<>();
	private Set<RoleDTO> roles = new HashSet<>();
	
	public OwnerDTO() {}
	
	public OwnerDTO(Owner entity) {
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.age = entity.getAge();
		this.gender = entity.getGender();
		this.photo = entity.getPhoto();
		this.email = entity.getEmail();
		entity.getPets().forEach(pet -> this.pets.add(new PetDTO(pet)));
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Set<RoleDTO> getRoles() {
		return roles;
	}
}
