package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.entities.enums.Gender;
import com.yannfigueiredo.petsarea.entities.enums.Type;

public class PetDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 2, max = 30, message = "O sobrenome deve ter entre 2 e 30 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String name;
	
	@PositiveOrZero(message = "O valor deve ser 0 ou maior.")
	private Integer age;
	
	@NotEmpty(message = "Deve ser escolhido um gênero.")
	private Gender gender;
	
	@NotEmpty(message = "Deve ser escolhido um tipo.")
	private Type type;
	
	@Size(min = 0, max = 250, message = "A descrição deve ter até 250 caracteres.")
	private String description;
	
	private String photo;
	private String ownerName;
	private Long ownerId;
	
	public PetDTO( ) {}
	
	public PetDTO(Pet entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.age = entity.getAge();
		this.gender = entity.getGender();
		this.type = entity.getType();
		this.description = entity.getDescription();
		this.photo = entity.getPhoto();
		this.ownerName = entity.getOwner().getFirstName() + " " + entity.getOwner().getLastName();
		this.ownerId = entity.getOwner().getId();
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public Long getOwnerId() {
		return ownerId;
	}
}
