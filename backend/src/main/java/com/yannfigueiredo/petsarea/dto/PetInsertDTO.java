package com.yannfigueiredo.petsarea.dto;

import java.io.Serializable;

import com.yannfigueiredo.petsarea.entities.enums.Gender;
import com.yannfigueiredo.petsarea.entities.enums.Type;

public class PetInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Integer age;
	private Gender gender;
	private Type type;
	private String description;
	private String photo;
	private Long ownerId;
	
	public PetInsertDTO() {}

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

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
