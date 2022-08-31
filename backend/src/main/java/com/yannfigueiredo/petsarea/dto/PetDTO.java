package com.yannfigueiredo.petsarea.dto;

import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.entities.enums.Gender;
import com.yannfigueiredo.petsarea.entities.enums.Type;

public class PetDTO {
	private Long id;
	private String name;
	private Integer age;
	private Gender gender;
	private Type type;
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
