package com.yannfigueiredo.petsarea.dto;

import java.time.LocalDate;

import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.entities.enums.Gender;

public class PetDTO {
	private Long id;
	private String name;
	private String age;
	private Gender gender;
	private String breed;
	private String description;
	private String photo;
	private LocalDate registerDate;
	private String ownerName;
	
	public PetDTO( ) {}
	
	public PetDTO(Pet entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.age = entity.getAge();
		this.gender = entity.getGender();
		this.breed = entity.getBreed();
		this.description = entity.getDescription();
		this.photo = entity.getPhoto();
		this.registerDate = entity.getRegisterDate();
		this.ownerName = entity.getOwner().getName();
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
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

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
