package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.enums.Gender;

public class OwnerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String firstName;
	
	@Size(min = 2, max = 30, message = "O sobrenome deve ter entre 2 e 30 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String lastName;
	
	@PositiveOrZero(message = "O valor deve ser 0 ou maior.")
	private Integer age;
	
	@NotNull(message = "Deve ser escolhido um gênero.")
	private Gender gender;
	
	private String photo;
	
	@Email(message = "O email inserido deve ser válido.")
	private String email;
	
	private List<PetDTO> pets = new ArrayList<>();
	
	@NotEmpty(message = "Deve existir pelo menos uma role.")
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
